package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier

data class PanierDTO(

    var id : String,

    var listeAchat : List<Achat>,

    var etatPanier: EtatPanier
)