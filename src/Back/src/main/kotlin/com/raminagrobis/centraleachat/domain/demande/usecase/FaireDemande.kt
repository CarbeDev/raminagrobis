package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import org.springframework.stereotype.Service

@Service
class FaireDemande(val repo : IDemandeRepo) {
    fun handle(demande: DemandeDTO){
        repo.saveDemande(demande)
    }
}