package com.raminagrobis.centraleachat.domain.fournisseur.adapter

import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey

interface IPropositionRepo {
    fun saveProposition(proposition: PropositionDTO)
    fun getPropositionsByProduit(refProduit: String): Iterable<PropositionDetail>
    fun getPropositionsBySociete(idSociete : Int): Iterable<PropositionDetail>

    fun getLowestPropositionPrixByPrix(refProduit : String): PropositionDetail

    fun deleteProposition(id: PropositionKey)
}