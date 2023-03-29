package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import org.springframework.stereotype.Service

@Service
class GetProduits(private val repo:IProduitRepo) {

    fun handle() : Iterable<Produit>{
        return repo.getProduits()
    }
}