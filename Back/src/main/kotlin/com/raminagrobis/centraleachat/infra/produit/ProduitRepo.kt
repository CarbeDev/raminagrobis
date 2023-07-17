package com.raminagrobis.centraleachat.infra.produit

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapper
import org.springframework.stereotype.Repository

@Repository
class ProduitRepo(private val repo : SQLProduit, private val mapper: ProduitMapper): IProduitRepo{
    override fun saveProduit(produit: ProduitDetail) {
        repo.save(mapper.toEntity(produit))
    }

    override fun saveProduit(produit: ProduitDTO) {
        repo.save(mapper.toEntity(produit))
    }

    override fun getProduits(): Iterable<ProduitDetail> {
       return repo.findAll().map { mapper.toDTO(it) }
    }

    override fun getProduitByRef(ref: String): ProduitDetail {
      return mapper.toDTO(repo.findById(ref).get())
    }
}