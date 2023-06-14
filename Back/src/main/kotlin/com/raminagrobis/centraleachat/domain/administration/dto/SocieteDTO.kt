package com.raminagrobis.centraleachat.domain.administration.dto

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.model.Achat

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
    var historique : List<Achat>
)

data class UserSociete(
    var nom : String,
    var email : String,
    var motDePasse : String,
    var role: Role?,
    var actif : Boolean,
)
