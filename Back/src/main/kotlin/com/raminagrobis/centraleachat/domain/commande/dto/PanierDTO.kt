package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity

data class PanierDTO(

    var id : String,

    var listeAchat : List<AchatEntity>,

    var etatPanier: EtatPanier
)