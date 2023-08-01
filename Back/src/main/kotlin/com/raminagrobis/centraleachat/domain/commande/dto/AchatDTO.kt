package com.raminagrobis.centraleachat.domain.commande.dto

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import java.math.BigDecimal
data class AchatMin(
    val idAdherent : Int,
    val refProduit: String,
    val idPanier : String,
    val quantite : Int
)
data class AchatDTO(
    val adherent : SocieteDTO,
    val produit: ProduitDetail,
    val panier : PanierDTO,
    val quantite : Int
)

data class AchatDetail(
    val adherent: SocieteDTO,
    val produit: ProduitDetail,
    val panier: PanierDTO,
    val quantite: Int,
    val prixUnitaire: BigDecimal? = null,
    val fournisseur: SocieteDTO? = null
)

