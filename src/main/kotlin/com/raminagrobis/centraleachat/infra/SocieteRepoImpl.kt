package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.societe.adapter.SocieteRepoInterface
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Component
import java.util.*

@Component
class SocieteRepoImpl(private val repo: SocieteRepo) : SocieteRepoInterface { //TODO Stop cycle and remove allow circular references
    override fun getAll(): Iterable<Societe> {
        return repo.findAll()
    }

    override fun saveSociete(societe: Societe) {
        repo.save(societe)
    }

    override fun isEmailUnique(email: String): Boolean {
        return repo.existsByEmail(email)
    }

    override fun findUserByID(id: Int): Optional<Societe> {
        return repo.findById(id)
    }

    override fun getNbCommandeByUser(societe: Societe): Int {
        TODO("Not yet implemented")
    }

    override fun deleteUser(societe: Societe) {
        repo.delete(societe)
    }
}