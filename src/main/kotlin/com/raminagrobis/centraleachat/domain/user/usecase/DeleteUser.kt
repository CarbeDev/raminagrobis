package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.User

class DeleteUser(val userRepo : UserRepoInterface){

    fun deleteUser(user : User){
        if(userRepo.getNbCommandeByUser(user) >= 1)  desactiveUser(user) else userRepo.deleteUser(user)
    }

    private fun desactiveUser(user: User) {
        user.actif = false
        InsertUser(userRepo).insertUserIntoDb(user)
    }
}