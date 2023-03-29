package com.raminagrobis.centraleachat.infra.produit

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import org.springframework.stereotype.Repository

@Repository
class ProduitRepo(private val repo : SQLProduit): IProduitRepo{
    override fun saveProduit(produit: Produit) {
        repo.save(produit)
    }

    override fun getProduits(): Iterable<Produit> {
       return repo.findAll()
    }

    override fun getProduitByRef(ref: String): Produit {
      return repo.findById(ref).get()
    }
}