package com.raminagrobis.centraleachat.domain.user.adapter

import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepoInterface {

    fun saveUser(user: User)
    fun isEmailUnique(email: String) : Boolean
    fun findUserByID(id : Int) : Optional<User>

    fun getNbCommandeByUser(user: User) : Int
    fun deleteUser(user: User)

}
