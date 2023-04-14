package com.raminagrobis.centraleachat.domain.commande.adapter

import com.raminagrobis.centraleachat.domain.commande.model.Achat

interface IAchatRepo {
    fun getAllAchat() : Iterable<Achat>
    fun getAchatByCommande(idPanier : String) : Iterable<Achat>
    fun getAchatByProduit(refProduit : String) : Iterable<Achat>
    fun getAchatByProduitAndCommande(refProduit: String, idPanier: String)
    fun saveAchat(achat: Achat)
    fun deleteAchat(achat: Achat)

}