package com.raminagrobis.centraleachat.domain.fournisseur.dto

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import java.math.BigDecimal

data class PropositionDTO(
    val produit: ProduitDTO,
    val societe: SocieteDTO,
    val prix : BigDecimal
)
