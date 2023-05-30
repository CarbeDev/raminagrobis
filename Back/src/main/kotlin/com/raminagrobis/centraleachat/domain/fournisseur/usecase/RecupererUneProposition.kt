package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import org.springframework.stereotype.Service

@Service
class RecupererPropositions(val repo : IPropositionRepo){

    fun handle(refProduit : String) : Iterable<Proposition>{
        return repo.getPropositionsByProduit(refProduit)
    }

    fun handle(idSociete : Int) : Iterable<Proposition>{
        return repo.getPropositionsBySociete(idSociete)
    }
}