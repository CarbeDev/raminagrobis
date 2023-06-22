package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande
import org.springframework.stereotype.Service

@Service
class RefuserDemande(val repo : IDemandeRepo) {

    fun handle(id : Int){
        val demande = repo.getDemande(id)
        demande.etat = EtatDemande.REFUSER
        repo.saveDemande(demande)
    }
}