package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import org.springframework.data.repository.CrudRepository

interface SQLAchat : CrudRepository<AchatEntity, AchatKey> {

    fun getAchatsByPanier_Id(idPanier: String) : Iterable<AchatEntity>
    fun getAchatsByProduit(refProduit : String) : Iterable<AchatEntity>

    fun getAchatsByProduitAndPanier(refProduit: String, idPanier: String) : Iterable<AchatEntity>
}