package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.stereotype.Service


@Service
class CreerSociete(private val societeRepo : ISocieteRepo) {

    fun handle(email: String, nom: String, role: Role) {
        val actif = true
        if (societeRepo.isEmailUnique(email)) societeRepo.saveSociete(Societe(nom, email, actif, role)) else throw EmailAlreadyUseException()
    }
}
