package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.AchatKey
import org.springframework.data.repository.CrudRepository

interface SQLAchat : CrudRepository<Achat, AchatKey> {

    fun getAchatsByPanier_Id(idPanier: String) : Iterable<Achat>
    fun getAchatsByProduit(refProduit : String) : Iterable<Achat>

    fun getAchatsByProduitAndPanier(refProduit: String, idPanier: String)
}