package com.raminagrobis.centraleachat.domain.administration.dto

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import java.util.*

data class SocieteDTO(
    var id : Int,
    var nom : String,
    var email : String,
    var role: Role?,
    var actif : Boolean
)

data class DetailSociete(
    var id : Int,
    var nom : String,
    var email : String,
    var role: Role?,
    var actif : Boolean,
    var dateInscription : Date,
    var historique : List<AchatEntity> = listOf()
)

data class UserSociete(
    var nom : String,
    var email : String,
    var motDePasse : String,
    var role: Role?,
    var dateInscription: Date,
    var actif : Boolean,
)

data class SocieteToCreate(
    val email : String,
    val nom : String,
    val role: Role
)
