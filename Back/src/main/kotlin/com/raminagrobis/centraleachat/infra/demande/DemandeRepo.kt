package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeInterface
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.mapper.DemandeMapper
import org.springframework.stereotype.Repository

@Repository
class DemandeRepo(val repo : DemandeSQL, val mapper : DemandeMapper) : IDemandeInterface{
    override fun saveDemande(demande: DemandeDTO) {
        repo.save(mapper.toEntity(demande))
    }
}