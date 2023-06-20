package com.raminagrobis.centraleachat.domain.administration.dto

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity

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
    var historique : List<AchatEntity>
)

data class UserSociete(
    var nom : String,
    var email : String,
    var motDePasse : String,
    var role: Role?,
    var actif : Boolean,
)
