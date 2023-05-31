package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeInterface
import com.raminagrobis.centraleachat.domain.demande.model.Demande
import org.springframework.stereotype.Repository

@Repository
class DemandeRepo(val repo : DemandeSQL) : IDemandeInterface{
    override fun saveDemande(demande: Demande) {
        repo.save(demande)
    }
}