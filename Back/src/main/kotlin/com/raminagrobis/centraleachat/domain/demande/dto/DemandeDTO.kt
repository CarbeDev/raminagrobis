package com.raminagrobis.centraleachat.domain.demande.dto

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande

data class DemandeDTO(
    var id : Int,
    var idSociete : Int,
    var nom : String,
    var description : String,
    var idCategorie : Int,
    var etat : EtatDemande = EtatDemande.EN_COURS
)
data class DemandeDetail(
    var id : Int,
    var societe : SocieteDTO,
    var nom : String,
    var description : String,
    var categorie: CategorieDTO,
    var etat : EtatDemande = EtatDemande.EN_COURS
)

data class DemandeGere(
    val produit: ProduitDetail,
    val idDemande : Int
)