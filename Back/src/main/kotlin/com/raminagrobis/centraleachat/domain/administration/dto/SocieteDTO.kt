package com.raminagrobis.centraleachat.domain.administration.dto

import com.raminagrobis.centraleachat.domain.administration.model.Role

data class SocieteDTO(
    val id : Int,
    val nom : String,
    val email : String,
    val role: Role?,
    val actif : Boolean
)
