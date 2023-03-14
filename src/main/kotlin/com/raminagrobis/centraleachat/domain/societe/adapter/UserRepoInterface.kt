package com.raminagrobis.centraleachat.domain.societe.adapter

import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepoInterface {

    fun getAll() : Iterable<Societe>
    fun saveUser(societe: Societe)
    fun isEmailUnique(email: String) : Boolean
    fun findUserByID(id : Int) : Optional<Societe>

    fun getNbCommandeByUser(societe: Societe) : Int
    fun deleteUser(societe: Societe)

}
