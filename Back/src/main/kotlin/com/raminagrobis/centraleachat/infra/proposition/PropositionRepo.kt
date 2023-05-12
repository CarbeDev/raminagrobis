package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import org.springframework.stereotype.Repository

@Repository
class PropositionRepo(private val repo : SQLProposition) : IPropositionRepo {
    override fun saveProposition(proposition: Proposition) {
        repo.save(proposition)
    }


    override fun deleteProposition(proposition: Proposition) {
        repo.delete(proposition)
    }

    override fun getPropositionsByProduit(refProduit: String): Iterable<Proposition> {
        return repo.getAllByProduitOrderByPrix(refProduit)
    }

    override fun getPropositionsBySociete(idSociete : Int): Iterable<Proposition> {
        return repo.getAllBySocieteOrderByPrix(idSociete)
    }
}