package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.exception.CantRemoveAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import org.springframework.stereotype.Service

@Service
class AnnulerAchat(private val repo: IAchatRepo) {

    fun handle(achat: AchatDTO) {
        if (achat.panier!!.etatPanier == EtatPanier.FERMER){
            throw CantRemoveAchatException("Le panier est fermé")
        }
        else {
            repo.deleteAchat(achat)
        }
    }
}