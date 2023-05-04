package com.raminagrobis.centraleachat.domain.connexion.model

import com.raminagrobis.centraleachat.domain.administration.model.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

interface Utilisateur {

    val email : String?
    val motDePasse : String?
    val role : Role?

    fun toSpringUser() : User{
        return User(email,motDePasse,getAuthority())
    }
    fun getAuthority() : Set<GrantedAuthority>{

        var authorities = HashSet<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(role.toString()))

        return authorities
    }
}