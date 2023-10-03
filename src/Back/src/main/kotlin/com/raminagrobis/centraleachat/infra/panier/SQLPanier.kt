package com.raminagrobis.centraleachat.infra.panier

import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import org.springframework.data.repository.CrudRepository

interface SQLPanier : CrudRepository<PanierEntity, String> {

    fun findAllByEtatPanier(etatPanier: EtatPanier) : Iterable<PanierEntity>
}