package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import java.math.BigDecimal

data class AchatDTO(
    val adherent : SocieteDTO,
    val produit: ProduitDTO,
    val panier : PanierDTO,
    val quantite : Int
)

data class AchatConfirme(
    val adherent: SocieteDTO,
    val produit: ProduitDTO,
    val panier: PanierDTO,
    val quantite: Int,
    val prix: BigDecimal,
    val fournisseur: SocieteDTO
)

