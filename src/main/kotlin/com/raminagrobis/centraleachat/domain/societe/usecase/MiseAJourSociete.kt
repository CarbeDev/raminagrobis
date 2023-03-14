package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Service

@Service
class MiseAJourSociete(val userRepo : UserRepoInterface) {

    fun handle(societe: Societe){
        if (userRepo.isEmailUnique(societe.email)) userRepo.saveUser(societe) else throw EmailAlreadyUseException()
    }
}