package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import org.springframework.stereotype.Service

@Service
class EnleverUnePropositionDePrix(private val repo:IPropositionRepo){

    fun handle(proposition: Proposition){
        repo.deleteProposition(proposition)
    }
}