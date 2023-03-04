package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepo : UserRepoInterface {
    override fun getAll(): Iterable<User> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun isEmailUnique(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findUserByID(id: Int): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun getNbCommandeByUser(user: User): Int {
        TODO("Not yet implemented")
    }

    override fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}