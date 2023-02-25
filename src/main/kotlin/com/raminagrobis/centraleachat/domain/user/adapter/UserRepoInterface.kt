package com.raminagrobis.centraleachat.domain.user.adapter

import com.raminagrobis.centraleachat.domain.user.model.User

interface UserRepoInterface {

    fun saveUser(user: User)
    fun isEmailUnique(email: String) : Boolean

}
