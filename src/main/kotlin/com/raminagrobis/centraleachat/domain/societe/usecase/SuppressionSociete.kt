package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.SocieteRepoInterface
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class SuppressionSociete(@Lazy val userRepo : SocieteRepoInterface){

    fun handle(societe : Societe){
        if(userRepo.getNbCommandeByUser(societe) >= 1) {
            desactiveSociete(societe)
        } else {
            userRepo.deleteUser(societe)
        }
    }

    private fun desactiveSociete(societe: Societe) {
        societe.actif = false
        if (userRepo.isEmailUnique(societe.email.toString())) userRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}