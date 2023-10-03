package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail
import org.springframework.stereotype.Service

@Service
class RecupererDemandeParId(val repo : IDemandeRepo){

    fun handle(id : Int) : DemandeDetail{
       return repo.getDemandeById(id)
    }
}