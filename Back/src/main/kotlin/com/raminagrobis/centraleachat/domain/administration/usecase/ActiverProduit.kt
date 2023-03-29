package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import org.springframework.stereotype.Service

@Service
class ActiverProduit(private val repo : IProduitRepo) {

    fun handle(ref : String){
        val produit = repo.getProduitByRef(ref)

        produit.actif = true
        repo.saveProduit(produit)
    }
}