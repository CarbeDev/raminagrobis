package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import org.springframework.stereotype.Service

@Service
class SupprimerSociete(private val societeRepo : ISocieteRepo){

    fun handle(id : Int){

        val societe = societeRepo.findSocieteByID(id)

        if(societe.historique.isNotEmpty()) {
            desactiveSociete(societe)
        } else {
            societeRepo.deleteSociete(societe)
        }
    }

    private fun desactiveSociete(societe: DetailSociete) {
        societe.actif = false
        if (societeRepo.isEmailUnique(societe.email)) societeRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}