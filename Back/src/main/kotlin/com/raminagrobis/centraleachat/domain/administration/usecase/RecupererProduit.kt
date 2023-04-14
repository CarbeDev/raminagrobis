package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import org.springframework.stereotype.Service

@Service
class RecupererProduit (private val repo : IProduitRepo) {

    fun handle(ref : String) : Produit {
        return repo.getProduitByRef(ref)
    }
}