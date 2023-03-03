package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class DeleteUser(val userRepo : UserRepoInterface){

    fun handle(user : User){
        if(userRepo.getNbCommandeByUser(user) >= 1) {
            desactiveUser(user)
        } else {
            userRepo.deleteUser(user)
        }
    }

    private fun desactiveUser(user: User) {
        user.actif = false
        InsertUser(userRepo).insertUserIntoDb(user)
    }
}