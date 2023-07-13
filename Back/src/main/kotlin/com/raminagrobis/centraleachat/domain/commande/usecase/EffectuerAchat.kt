package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import org.springframework.stereotype.Service

@Service
class EffectuerAchat(val repo : IAchatRepo) {

    fun handle(achat: AchatDTO){

        if (achat.adherent!!.role == Role.FOURNISSEUR){
            throw IncorrectRoleSocieteException()
        } else if(achat.panier!!.etatPanier == EtatPanier.FERMER){
            throw  CantAddAchatException("Le panier est ferme")
        } else {
            repo.saveAchat(achat)
        }

    }
}