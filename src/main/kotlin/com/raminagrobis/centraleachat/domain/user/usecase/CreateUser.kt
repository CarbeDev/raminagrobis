package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.Role
import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class CreateUser(private val userRepo : UserRepoInterface) {

    fun createUser(email: String, prenom: String, nom: String, role: Role) {
        val actif = true
        InsertUser(userRepo).insertUserIntoDb(User(email, prenom, nom, role, actif))
    }
}