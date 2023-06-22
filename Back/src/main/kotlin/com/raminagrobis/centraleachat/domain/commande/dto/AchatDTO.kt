package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO

data class AchatDTO(
    val societe : SocieteDTO,
    val produit: ProduitDTO,
    val panier : PanierDTO,
    val quantite : Int
)

