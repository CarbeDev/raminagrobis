package com.raminagrobis.centraleachat.infra.panier

import com.raminagrobis.centraleachat.domain.commande.model.Etat
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import org.springframework.data.repository.CrudRepository

interface SQLPanier : CrudRepository<Panier, String> {

    fun findAllByEtat(etat: Etat) : Iterable<Panier>
}