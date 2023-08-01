package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import org.springframework.stereotype.Service

@Service
class SupprimerUnePropositionDePrix(private val repo:IPropositionRepo){

    fun handle(proposition: PropositionKey){
        repo.deleteProposition(proposition)
    }
}