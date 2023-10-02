package com.raminagrobis.centraleachat.domain.demande.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande
import org.springframework.stereotype.Service

@Service
class AccepterDemande(val repo : IDemandeRepo, val produitRepo : IProduitRepo) {

    fun handle(demande: DemandeGere){
        demande.creerProduit()
        demande.miseAJourDeLaDemande()
    }

    private fun DemandeGere.creerProduit(){
        produitRepo.saveProduit(this.produit)
    }

    private fun DemandeGere.miseAJourDeLaDemande(){
        repo.saveDemande(repo.getDemandeById(this.idDemande).copy(etat = EtatDemande.ACCEPTE))
    }
}