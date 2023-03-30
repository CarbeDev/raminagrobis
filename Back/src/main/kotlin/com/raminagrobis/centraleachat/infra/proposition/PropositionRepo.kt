package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import org.springframework.stereotype.Repository

@Repository
class PropositionRepo(private val repo : SQLProposition) : IPropositionRepo {
    override fun saveProposition(proposition: Proposition) {
        TODO("Not yet implemented")
    }
}