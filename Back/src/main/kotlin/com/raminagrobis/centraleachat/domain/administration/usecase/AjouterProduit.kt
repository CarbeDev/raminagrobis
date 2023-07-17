package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import org.springframework.stereotype.Service

@Service
class AjouterProduit(private val repo : IProduitRepo){

    fun handle(produit: ProduitDTO){
        repo.saveProduit(produit)
    }
}