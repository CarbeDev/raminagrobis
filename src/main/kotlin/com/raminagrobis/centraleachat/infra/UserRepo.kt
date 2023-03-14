package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.societe.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepo : UserRepoInterface {
    override fun getAll(): Iterable<Societe> {
        TODO("Not yet implemented")
    }

    override fun saveUser(societe: Societe) {
        TODO("Not yet implemented")
    }

    override fun isEmailUnique(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findUserByID(id: Int): Optional<Societe> {
        TODO("Not yet implemented")
    }

    override fun getNbCommandeByUser(societe: Societe): Int {
        TODO("Not yet implemented")
    }

    override fun deleteUser(societe: Societe) {
        TODO("Not yet implemented")
    }
}