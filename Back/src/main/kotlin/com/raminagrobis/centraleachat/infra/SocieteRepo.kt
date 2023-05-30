package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.stereotype.Repository

@Repository
class SocieteRepo(private val repo: SQLSociete) : ISocieteRepo {
    override fun getAll(): Iterable<Societe> {
        return repo.findAll()
    }

    override fun getByEmail(email: String): Societe? {
        return repo.getSocieteByEmail(email)
    }

    override fun saveSociete(societe: Societe) {
        repo.save(societe)
    }

    override fun isEmailUnique(email: String): Boolean {
        return repo.existsByEmail(email)
    }

    override fun findSocieteByID(id: Int): Societe {
        return repo.findById(id).orElseThrow()
    }

    override fun getNbCommandeBySociete(societe: Societe): Int {
        TODO("Not yet implemented")
    }

    override fun deleteSociete(societe: Societe) {
        repo.delete(societe)
    }
}