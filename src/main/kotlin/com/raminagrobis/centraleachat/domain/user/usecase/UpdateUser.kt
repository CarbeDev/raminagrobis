package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.Role
import com.raminagrobis.centraleachat.domain.user.model.User

class UpdateUser(val userRepo : UserRepoInterface) {

    fun updateUser(id: Int,email: String, prenom: String, nom: String, role: Role){
        InsertUserIntoDB(userRepo).insertUserIntoDb(User(id,email, prenom, nom, role))
    }
}