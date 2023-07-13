package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.domain.administration.mapper.SocieteMapper
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.springframework.stereotype.Repository

@Repository
class SocieteRepo(private val repo: SQLSociete, val mapper: SocieteMapper) : ISocieteRepo {
    override fun getAll(): Iterable<SocieteDTO> {
        return repo.findAll().map { mapper.toDTO(it) }
    }

    override fun getByEmail(email: String): SocieteEntity {
        return repo.getSocieteByEmail(email).orElseThrow()
    }

    override fun saveSociete(societe: SocieteDTO) {
        repo.save(mapper.toEntity(societe))
    }

    override fun saveSociete(societe: DetailSociete) {
        repo.save(mapper.toEntity(societe))
    }

    override fun saveSociete(societe: UserSociete) {
        repo.save(mapper.toEntity(societe))
    }

    override fun isEmailUnique(email: String): Boolean {
        return repo.existsByEmail(email)
    }

    override fun findSocieteByID(id: Int): DetailSociete {
        return mapper.toDetail(repo.findById(id).orElseThrow())
    }

    override fun deleteSociete(idSociete: Int) {
        repo.deleteById(idSociete)
    }
}