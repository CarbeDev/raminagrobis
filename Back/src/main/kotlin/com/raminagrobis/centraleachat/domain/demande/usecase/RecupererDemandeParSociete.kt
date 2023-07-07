package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import org.springframework.stereotype.Service

@Service
class RecupererDemandeParSociete(val repo : IDemandeRepo) {

    fun handle(idSociete : Int) : Iterable<DemandeDTO>{
        return repo.getDemandesBySociete(idSociete)
    }
}