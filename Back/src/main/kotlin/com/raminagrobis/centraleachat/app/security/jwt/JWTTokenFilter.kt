package com.raminagrobis.centraleachat.app.security.jwt

import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.infra.utilisateur.UtilisateurRepo
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter


@Configuration
class JWTTokenFilter(val jwtTokenUtil: JWTTokenUtil, val utilisateurRepo: UtilisateurRepo): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = getToken(request)
            if (token != null && jwtTokenUtil.validateToken(token)){
                val auth = getAuthFromToken(token)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e : Exception){
            println(e)
        }

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest) : String? {
        return request.getHeader("Authorization")
    }

    private fun getAuthFromToken(token : String) : UsernamePasswordAuthenticationToken{
        val utilisateur = getUtilisateurFromToken(token)
        return UsernamePasswordAuthenticationToken(utilisateur.email,null, utilisateur.getAuthority())
    }

    private fun getUtilisateurFromToken(token : String) : Utilisateur{
        var utilisateur :Utilisateur
        val email = jwtTokenUtil.getUsernameFromToken(token)
        try {
            utilisateur = utilisateurRepo.findAdminByEmail(email)
        } catch (e : Exception){
            utilisateur = utilisateurRepo.findSocieteByEmail(email)
        }

        return utilisateur
    }
}
