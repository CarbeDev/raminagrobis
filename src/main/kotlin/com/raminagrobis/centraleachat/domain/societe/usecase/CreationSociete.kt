package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Role
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Service

@Service
class CreationSociete(private val userRepo : UserRepoInterface) {

    fun handle(email: String, nom: String, role: Role) {
        val actif = true
        if (userRepo.isEmailUnique(email)) userRepo.saveUser(Societe(email, nom, role, actif)) else throw EmailAlreadyUseException()
    }
}