package com.raminagrobis.centraleachat.app.security.jwt

import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.core.env.Environment
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey

@Service
class JWTTokenUtil(env: Environment): IJWTTokenUtil {

    val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()

    var secret: String? = null

    var KEY: SecretKey? = null

    init {
        this.secret = env.getProperty("jwt.secret")
        this.KEY = Keys.hmacShaKeyFor(secret!!.toByteArray(StandardCharsets.UTF_8))
    }

    override fun generateToken(user: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, user.username)
    }

    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken<String>(token, Function<Claims, String> { obj: Claims -> obj.subject })
    }

    fun getExpiration(token: String): String {
        return getClaimFromToken<Date>(token, Function<Claims, Date> { obj: Claims -> obj.expiration }).toString()
    }

    fun getIssuedAt(token: String): String {
        return getClaimFromToken<Date>(token, Function<Claims, Date> { obj: Claims -> obj.issuedAt }).toString()
    }

    fun validateToken(token: String, user: UserDetails): Boolean {
        return getUsernameFromToken(token) == user.username && !isTokenExpired(token)
    }


    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }


    fun doGenerateToken(claims: Map<String, Any?>, subject: String): String {
        return Jwts.builder().setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(this.KEY)
            .compact()
    }

    fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(this.KEY).build().parseClaimsJws(token).getBody()
    }

    fun isTokenExpired(token: String): Boolean {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }.before(Date())
    }

}
