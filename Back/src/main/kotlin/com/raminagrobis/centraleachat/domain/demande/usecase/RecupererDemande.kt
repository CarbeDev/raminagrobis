package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import org.springframework.stereotype.Service

@Service
class RecupererDemande(val repo : IDemandeRepo){

    fun handle(id : Int) : DemandeDTO{
       return repo.getDemande(id)
    }
}