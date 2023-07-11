package com.raminagrobis.centraleachat.domain.commande.config

import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.builder.PanierBuilder
import com.raminagrobis.centraleachat.domain.commande.dto.AchatConfirme
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.PanierConfirme
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

@Configuration
@EnableScheduling
class CreationPanierTask(val panierRepo : IPanierRepo, val propositionRepo : IPropositionRepo) {

    @Scheduled(cron = "0 0 1 * * MON")
    fun handle(){
        fermerAncienPanier()
        creerNouveauPanier()
    }

    private fun creerNouveauPanier() {
        val panier = PanierBuilder().build(Calendar.getInstance())
        panierRepo.savePanier(panier)
    }

    private fun fermerAncienPanier(){
        val panierOuverts = panierRepo.getPaniersOuvert()

        val panierFermes =panierOuverts.map {
            PanierConfirme(
                id = it.id,
                listeAchat = it.listeAchat.map { attribuerPrixAuxAchats(it) },
                etatPanier = EtatPanier.FERMER
            )
        }

        panierRepo.savePaniers(panierFermes)

    }

    private fun attribuerPrixAuxAchats(achat : AchatDTO) : AchatConfirme{

        val meilleurProposition = propositionRepo.getLowestPropositionPrixByPrix(achat.produit.reference)

        return AchatConfirme(
            adherent = achat.adherent,
            produit = achat.produit,
            panier = achat.panier,
            quantite = achat.quantite,
            prix = meilleurProposition.prix,
            fournisseur = meilleurProposition.societe
        )
    }
}