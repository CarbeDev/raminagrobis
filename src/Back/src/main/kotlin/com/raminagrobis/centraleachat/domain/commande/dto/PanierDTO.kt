package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier

data class PanierDTO(

    var id : String,

    var listeAchat : List<AchatDTO>,

    var etatPanier: EtatPanier
)

data class PanierConfirme(
    var id : String,
    var listeAchat: List<AchatDetail>,
    var etatPanier: EtatPanier
)