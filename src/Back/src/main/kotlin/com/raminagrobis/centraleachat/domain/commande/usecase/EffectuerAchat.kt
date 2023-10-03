package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import org.springframework.stereotype.Service

@Service
class EffectuerAchat(val repoAchat : IAchatRepo, val repoSociete : ISocieteRepo, val repoPanier : IPanierRepo) {

    fun handle(achat: AchatMin){

        val adherent = repoSociete.findSocieteByID(achat.idAdherent)
        val panier = repoPanier.findById(achat.idPanier)

        if (adherent.role == Role.FOURNISSEUR){
            throw IncorrectRoleSocieteException()
        } else if(panier.etatPanier == EtatPanier.FERMER){
            throw  CantAddAchatException("Le panier est ferme")
        } else {
            repoAchat.saveAchat(achat)
        }

    }
}