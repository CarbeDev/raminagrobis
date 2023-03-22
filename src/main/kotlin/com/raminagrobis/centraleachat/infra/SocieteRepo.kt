package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.societe.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SocieteRepo(private val repo: SQLSociete) : ISocieteRepo {
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

    override fun getNbCommandeBySociete(societe: Societe): Int {
        TODO("Not yet implemented")
    }

    override fun deleteSociete(societe: Societe) {
        repo.delete(societe)
    }
}