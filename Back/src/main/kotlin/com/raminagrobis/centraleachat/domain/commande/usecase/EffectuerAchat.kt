package com.raminagrobis.centraleachat.domain.commande.usecase

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import org.springframework.stereotype.Service

@Service
class EffectuerAchat() {

    fun handle(achat: Achat){
        if (achat.societe!!.role == Role.FOURNISSEUR){
            throw IncorrectRoleSocieteException()
        }
    }
}