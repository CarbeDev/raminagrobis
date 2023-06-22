package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeInterface
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import org.springframework.stereotype.Service

@Service
class FaireDemande(val repo : IDemandeInterface) {
    fun handle(demande: DemandeDTO){
        repo.saveDemande(demande)
    }
}