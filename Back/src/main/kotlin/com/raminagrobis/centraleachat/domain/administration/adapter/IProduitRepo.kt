package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail

interface IProduitRepo {
    fun saveProduit(produit: ProduitDetail)
    fun saveProduit(produit : ProduitDTO)
    fun getProduits(): Iterable<ProduitDetail>
    fun getProduitByRef(ref: String): ProduitDetail
}