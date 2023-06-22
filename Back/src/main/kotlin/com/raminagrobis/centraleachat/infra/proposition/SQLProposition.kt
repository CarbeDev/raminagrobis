package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionEntity
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import org.springframework.data.repository.CrudRepository

interface SQLProposition : CrudRepository<PropositionEntity, PropositionKey> {
    fun getAllByProduitOrderByPrix(refProduit : String) : Iterable<PropositionEntity>

    fun getAllBySocieteOrderByPrix(idSociete : Int) : Iterable<PropositionEntity>
}
