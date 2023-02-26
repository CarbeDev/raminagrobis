package com.raminagrobis.centraleachat.domain.user.adapter

import com.raminagrobis.centraleachat.domain.user.model.User
import java.util.Optional

interface UserRepoInterface {

    fun saveUser(user: User)
    fun isEmailUnique(email: String) : Boolean
    fun findUserByID(id : Int) : Optional<User>

}
