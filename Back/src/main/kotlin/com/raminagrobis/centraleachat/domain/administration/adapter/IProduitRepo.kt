package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO

interface IProduitRepo {
    fun saveProduit(produit: ProduitDTO)
    fun getProduits(): Iterable<ProduitDTO>
    fun getProduitByRef(ref: String): ProduitDTO
}