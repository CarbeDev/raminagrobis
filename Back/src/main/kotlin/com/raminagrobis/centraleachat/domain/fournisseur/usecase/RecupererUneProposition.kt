package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import org.springframework.stereotype.Service

@Service
class RecupererPropositions(val repo : IPropositionRepo){

    fun handle(refProduit : String) : Iterable<PropositionDTO>{
        return repo.getPropositionsByProduit(refProduit)
    }

    fun handle(idSociete : Int) : Iterable<PropositionDTO>{
        return repo.getPropositionsBySociete(idSociete)
    }
}