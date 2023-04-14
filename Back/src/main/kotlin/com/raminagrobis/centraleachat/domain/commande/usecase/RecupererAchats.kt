package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import org.springframework.stereotype.Service

@Service
class RecupererAchats(private val repo : IAchatRepo) {

    fun handle(refProduit : String,idPanier : String) : Iterable<Achat>{
        return repo.getAchatByCommande(idPanier)
    }

}