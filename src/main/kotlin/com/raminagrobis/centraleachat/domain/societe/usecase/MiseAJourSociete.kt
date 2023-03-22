package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class MiseAJourSociete(@Lazy val userRepo : ISocieteRepo) {

    fun handle(societe: Societe){
        if (userRepo.isEmailUnique(societe.email.toString())) userRepo.saveSociete(societe) else throw EmailAlreadyUseException()
    }
}