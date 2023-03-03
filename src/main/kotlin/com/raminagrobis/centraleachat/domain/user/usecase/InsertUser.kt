package com.raminagrobis.centraleachat.domain.user.usecase

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class InsertUser(val userRepo: UserRepoInterface) {

    fun insertUserIntoDb(user: User){
        if (userRepo.isEmailUnique(user.email)) userRepo.saveUser(user) else throw EmailAlreadyUseException()
    }
}