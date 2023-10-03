package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import org.springframework.stereotype.Service

@Service
class RecupererAchats(private val repo : IAchatRepo) {

    fun handle(refProduit : String?,idPanier : String?) : Iterable<AchatDetail>{
        if (refProduit != null && idPanier != null){
            return repo.getAchatsByProduitAndCommande(refProduit, idPanier)
        } else if (refProduit != null){
            return repo.getAchatsByProduit(refProduit)
        }
        else if(idPanier != null){
            return repo.getAchatsByCommande(idPanier)
        }
        return listOf()
    }

}