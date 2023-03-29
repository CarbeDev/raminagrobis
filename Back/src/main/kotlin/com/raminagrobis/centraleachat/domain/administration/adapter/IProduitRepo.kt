package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.model.Produit

interface IProduitRepo {
    fun saveProduit(produit: Produit)
    fun getProduits(): Iterable<Produit>
    fun getProduitByRef(ref: String): Produit
}