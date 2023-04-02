package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.commande.model.Achat

interface IAchatRepo {
    fun saveAchat(achat: Achat)
}