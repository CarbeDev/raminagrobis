package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class MiseAJourSociete(@Lazy val userRepo : ISocieteRepo) {

    fun handle(societe: Societe){
        if (userRepo.isEmailUnique(societe.email.toString())) userRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}