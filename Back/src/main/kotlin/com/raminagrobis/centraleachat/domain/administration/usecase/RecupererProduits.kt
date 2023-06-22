package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapper
import org.springframework.stereotype.Service

@Service
class RecupererProduits(private val repo:IProduitRepo, private val mapper: ProduitMapper) {

    fun handle() : Iterable<ProduitDTO>{
        return repo.getProduits()
    }
}