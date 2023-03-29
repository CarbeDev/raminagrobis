package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import org.springframework.stereotype.Service

@Service
class AjouterUnProduit(private val repo : IProduitRepo){

    fun handle(produit: Produit){
        repo.saveProduit(produit)
    }
}