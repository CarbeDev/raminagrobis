package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.commande.dto.PanierConfirme
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO

interface IPanierRepo {

    fun getPaniersOuvert() : Iterable<PanierDTO>
    fun savePanier(panier: PanierDTO)
    fun savePaniers(paniers : Iterable<PanierConfirme>)
}