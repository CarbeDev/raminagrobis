package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.mapper.DemandeMapper
import org.springframework.stereotype.Repository

@Repository
class DemandeRepo(val repo : DemandeSQL, val mapper : DemandeMapper) : IDemandeRepo{
    override fun saveDemande(demande: DemandeDTO) {
        repo.save(mapper.toEntity(demande))
    }

    override fun getDemandeById(id: Int): DemandeDTO {
        return mapper.toDTO(repo.findById(id).orElseThrow())
    }

    override fun getDemandesBySociete(id: Int): Iterable<DemandeDTO> {
        return repo.findAllBySocieteId(id).map { mapper.toDTO(it) }
    }

    override fun getDemandes(): Iterable<DemandeDTO> {
       return repo.findAll().map { mapper.toDTO(it) }
    }
}