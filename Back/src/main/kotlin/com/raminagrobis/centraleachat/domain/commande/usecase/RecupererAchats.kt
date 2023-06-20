package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import org.springframework.stereotype.Service

@Service
class RecupererAchats(private val repo : IAchatRepo) {

    fun handle(refProduit : String?,idPanier : String?) : Iterable<AchatDTO>?{
        if (refProduit != null && idPanier != null){
            return repo.getAchatByProduitAndCommande(refProduit, idPanier)
        } else if (refProduit != null){
            return repo.getAchatByProduit(refProduit)
        }
        else if(idPanier != null){
            return repo.getAchatByCommande(idPanier)
        }
        return null
    }

}