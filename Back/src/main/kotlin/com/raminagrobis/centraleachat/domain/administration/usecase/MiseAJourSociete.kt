package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import org.springframework.stereotype.Service

@Service
class MiseAJourSociete(private val userRepo : ISocieteRepo) {

    fun handle(societe: SocieteDTO){
        if (userRepo.isEmailUnique(societe.email)) userRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}