package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionEntity
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import org.springframework.data.repository.CrudRepository

interface SQLProposition : CrudRepository<PropositionEntity, PropositionKey> {
    fun getAllByPropositionKeyReferenceOrderByPrix(refProduit : String) : Iterable<PropositionEntity>

    fun getAllByPropositionKeyIdSociete(idSociete : Int) : Iterable<PropositionEntity>

    fun getFirstByPropositionKeyReferenceOrderByPrix(refProduit: String) : PropositionEntity
}
