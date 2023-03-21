package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.SocieteRepoInterface
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Role
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class CreationSociete(@Lazy private val societeRepo : SocieteRepoInterface) {

    fun handle(email: String, nom: String, role: Role) {
        val actif = true
        if (societeRepo.isEmailUnique(email)) societeRepo.saveSociete(Societe(nom, email, actif, role)) else throw EmailAlreadyUseException()
    }
}