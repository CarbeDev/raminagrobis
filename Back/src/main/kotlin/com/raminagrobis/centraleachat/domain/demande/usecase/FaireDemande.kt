package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeInterface
import com.raminagrobis.centraleachat.domain.demande.model.Demande
import org.springframework.stereotype.Service

@Service
class FaireDemande(val repo : IDemandeInterface) {
    fun handle(demande: Demande){
        repo.saveDemande(demande)
    }
}