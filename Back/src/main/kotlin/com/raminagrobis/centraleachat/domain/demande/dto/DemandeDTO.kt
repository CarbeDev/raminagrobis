package com.raminagrobis.centraleachat.domain.demande.dto

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande

data class DemandeDTO(
    var id : Int,
    var societe : SocieteDTO,
    var nom : String,
    var description : String,
    var categorie: CategorieDTO,
    var etat : EtatDemande = EtatDemande.EN_COURS
)