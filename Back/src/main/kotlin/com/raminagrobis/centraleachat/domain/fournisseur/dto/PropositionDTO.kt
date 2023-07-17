package com.raminagrobis.centraleachat.domain.fournisseur.dto

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import java.math.BigDecimal

data class PropositionDTO(
    val produit: ProduitDetail,
    val societe: SocieteDTO,
    val prix : BigDecimal
)
