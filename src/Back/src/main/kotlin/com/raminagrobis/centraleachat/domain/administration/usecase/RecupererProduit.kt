package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import org.springframework.stereotype.Service

@Service
class RecupererProduit (private val repo : IProduitRepo) {

    fun handle(ref : String) : ProduitDetail {
        return repo.getProduitByRef(ref)
    }
}