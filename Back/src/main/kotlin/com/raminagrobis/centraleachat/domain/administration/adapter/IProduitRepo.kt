package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.model.Produit

interface IProduitRepo {
    fun saveProduit(produit: Produit)
}