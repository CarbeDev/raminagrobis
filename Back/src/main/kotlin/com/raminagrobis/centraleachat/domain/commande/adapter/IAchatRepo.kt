package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey

interface IAchatRepo {
    fun getAllAchat() : Iterable<AchatDetail>
    fun getAchatsByCommande(idPanier : String) : Iterable<AchatDetail>
    fun getAchatsByProduit(refProduit : String) : Iterable<AchatDetail>
    fun getAchatsByProduitAndCommande(refProduit: String, idPanier: String) : Iterable<AchatDetail>
    fun getAchat(key: AchatKey) : AchatDetail
    fun saveAchat(achat: AchatMin)
    fun deleteAchat(achat: AchatDetail)

    fun getNbAchatBySociete(societe : DetailSociete) : Int

}