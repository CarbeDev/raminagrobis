package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import org.springframework.stereotype.Service

@Service
class SupprimerUnePropositionDePrix(private val repo:IPropositionRepo){

    fun handle(proposition: PropositionDTO){
        repo.deleteProposition(proposition)
    }
}