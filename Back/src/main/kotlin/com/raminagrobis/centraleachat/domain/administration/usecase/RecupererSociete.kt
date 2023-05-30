package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import org.springframework.stereotype.Service

@Service
class RecupererSociete(private val repo : ISocieteRepo){

    fun handle(id : Int) : SocieteDTO{
        return repo.findSocieteByID(id).let { societe -> SocieteDTO(
            id = societe.id,
            nom =  societe.nom,
            email = societe.email,
            role = societe.role,
            actif = societe.actif
        ) }
    }
}