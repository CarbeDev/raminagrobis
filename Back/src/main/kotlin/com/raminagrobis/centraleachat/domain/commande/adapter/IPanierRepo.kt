package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.commande.model.Panier

interface IPanierRepo {

    fun getPaniersOuvert() : Iterable<Panier>
    fun savepanier(panier: Panier)
}