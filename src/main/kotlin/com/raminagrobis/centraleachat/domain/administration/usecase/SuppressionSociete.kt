package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.stereotype.Service

@Service
class SuppressionSociete(private val societeRepo : ISocieteRepo){

    fun handle(id : Int){

        val societe = societeRepo.findSocieteByID(id)

        if(societeRepo.getNbCommandeBySociete(societe) >= 1) {
            desactiveSociete(societe)
        } else {
            societeRepo.deleteSociete(societe)
        }
    }

    private fun desactiveSociete(societe: Societe) {
        societe.actif = false
        if (societeRepo.isEmailUnique(societe.email.toString())) societeRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}