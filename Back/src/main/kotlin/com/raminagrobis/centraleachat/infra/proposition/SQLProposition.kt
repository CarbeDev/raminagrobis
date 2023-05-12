package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.model.PropositionKey
import org.springframework.data.repository.CrudRepository

interface SQLProposition : CrudRepository<Proposition, PropositionKey> {
    fun getAllByProduitOrderByPrix(refProduit : String) : Iterable<Proposition>

    fun getAllBySocieteOrderByPrix(idSociete : Int) : Iterable<Proposition>
}
