package com.raminagrobis.centraleachat.domain.connexion.adapter

import org.springframework.security.core.userdetails.UserDetails

interface IJWTTokenUtil {
    fun generateToken(user: UserDetails) : String
}