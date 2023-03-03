package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class UpdateUser(val userRepo : UserRepoInterface) {

    fun updateUser(user: User){
        InsertUser(userRepo).insertUserIntoDb(user)
    }
}