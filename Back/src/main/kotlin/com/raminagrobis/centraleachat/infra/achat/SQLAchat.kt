package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.AchatKey
import org.springframework.data.repository.CrudRepository

interface SQLAchat : CrudRepository<Achat, AchatKey> {
}