package com.raminagrobis.centraleachat.app.security.jwt

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.infra.redis.session.SessionRepo
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component


@Component
@Order(1)
class JWTTokenFilter(val jwtTokenUtil: JWTTokenUtil, val repo : SessionRepo): Filter {

    private val logger = LoggerFactory.getLogger(JWTTokenFilter::class.java)

    private val permitAllList = listOf("/connexion","/token","/swagger-ui","/v3")

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        try {
            val req = request!! as HttpServletRequest
            if (haveRight(req)){
                filterChain!!.doFilter(request,response)
            }
            else {
                logger.warn("Pas les droits")
                val res = response as HttpServletResponse
                res.status = HttpStatus.FORBIDDEN.value()
                return
            }
        } catch (e : Exception){
            logger.warn(e.toString())
            val res = response as HttpServletResponse
            res.status = HttpStatus.FORBIDDEN.value()
            return
        }
    }

    private fun haveRight(request : HttpServletRequest) : Boolean{
        val path = request.requestURI
        val token = request.token()
        with(path){
            return if (isPermitAll()) true
            else {
                if (token == null) return false

                val role = jwtTokenUtil.getUtilisateurFromToken(token).role!!
                if (token.isValid(request.remoteAddr)){
                    when{
                        contains("/admin") -> role == Role.ADMIN
                        contains("/fournisseur") -> role == Role.FOURNISSEUR
                        contains("/adherent")-> role == Role.ADHERENT
                        else -> true
                    }
                } else false
            }
        }
    }
    private fun HttpServletRequest.token() : String? {
        return this.getHeader("Authorization")
    }

    private fun String.isValid(ip : String) : Boolean{
        val session = repo.findByIp(ip)
        return jwtTokenUtil.validateToken(this) && session != null && session.jwt == this
    }

    private fun String.isPermitAll() : Boolean{
        return permitAllList.stream().anyMatch{ this.contains(it) }
    }
}
