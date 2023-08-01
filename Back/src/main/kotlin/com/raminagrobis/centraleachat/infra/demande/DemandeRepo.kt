package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail
import com.raminagrobis.centraleachat.domain.demande.mapper.DemandeMapper
import org.springframework.stereotype.Repository

@Repository
class DemandeRepo(val repo : DemandeSQL, val mapper : DemandeMapper) : IDemandeRepo{
    override fun saveDemande(demande: DemandeDetail) {
        repo.save(mapper.toEntity(demande))
    }

    override fun saveDemande(demandeDTO: DemandeDTO) {
        repo.save(mapper.toEntity(demandeDTO))
    }

    override fun getDemandeById(id: Int): DemandeDetail {
        return mapper.toDTO(repo.findById(id).orElseThrow())
    }

    override fun getDemandesBySociete(id: Int): Iterable<DemandeDetail> {
        return repo.findAllBySocieteId(id).map { mapper.toDTO(it) }
    }

    override fun getDemandes(): Iterable<DemandeDetail> {
       return repo.findAll().map { mapper.toDTO(it) }
    }
}