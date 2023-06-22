package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande
import org.springframework.stereotype.Service

@Service
class AccepterDemande(val repo : IDemandeRepo, val produitRepo : IProduitRepo) {

    fun handle(demande: DemandeGere){
        creerProduit(demande)
        miseAJourDeLaDemande(demande)
    }

    private fun creerProduit(demande : DemandeGere){
        produitRepo.saveProduit(demande.produit)
    }

    private fun miseAJourDeLaDemande(demandeGere : DemandeGere){
        val demande = repo.getDemande(demandeGere.idDemande)
        demande.etat = EtatDemande.ACCEPTE
        repo.saveDemande(demande)
    }
}