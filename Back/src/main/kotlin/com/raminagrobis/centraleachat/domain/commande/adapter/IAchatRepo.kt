package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO

interface IAchatRepo {
    fun getAllAchat() : Iterable<AchatDTO>
    fun getAchatByCommande(idPanier : String) : Iterable<AchatDTO>
    fun getAchatByProduit(refProduit : String) : Iterable<AchatDTO>
    fun getAchatByProduitAndCommande(refProduit: String, idPanier: String) : Iterable<AchatDTO>
    fun saveAchat(achat: AchatDTO)
    fun deleteAchat(achat: AchatDTO)

    fun getNbAchatBySociete(societe : DetailSociete) : Int

}