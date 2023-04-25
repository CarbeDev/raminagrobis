package com.raminagrobis.centraleachat.domain.connexion.model

import com.raminagrobis.centraleachat.domain.administration.model.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

interface Utilisateur {

    val email : String?
    val motDePasse : String?
    val role : Role?

    fun getAuthority() : Set<GrantedAuthority>{

        var authorities = HashSet<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(role.toString()))

        return authorities
    }
}