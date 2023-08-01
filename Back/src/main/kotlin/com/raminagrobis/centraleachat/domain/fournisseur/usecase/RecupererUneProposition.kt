package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import org.springframework.stereotype.Service

@Service
class RecupererPropositions(val repo : IPropositionRepo){

    fun handle(refProduit : String) : Iterable<PropositionDetail>{
        return repo.getPropositionsByProduit(refProduit)
    }

    fun handle(idSociete : Int) : Iterable<PropositionDetail>{
        return repo.getPropositionsBySociete(idSociete)
    }
}