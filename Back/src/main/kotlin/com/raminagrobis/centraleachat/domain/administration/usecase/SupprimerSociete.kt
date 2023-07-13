package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import org.springframework.stereotype.Service

@Service
class SupprimerSociete(private val societeRepo : ISocieteRepo, private val achatRepo : IAchatRepo){

    fun handle(id : Int){

        val societe = societeRepo.findSocieteByID(id)

        if(achatRepo.getNbAchatBySociete(societe) >= 1) {
            desactiveSociete(societe)
        } else {
            societeRepo.deleteSociete(id)
        }
    }

    private fun desactiveSociete(societe: DetailSociete) {

        societe.actif = false
        societeRepo.saveSociete(societe)
    }
}