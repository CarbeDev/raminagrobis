package com.raminagrobis.centraleachat.usecase.panier

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.config.CreationPanierTask
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.PanierConfirme
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*


@ExtendWith(MockitoExtension::class)
class CreationPanierTaskTest {

    @Mock
    private lateinit var repoPanier : IPanierRepo
    @Mock
    private lateinit var repoProposition : IPropositionRepo

    @InjectMocks
    private lateinit var taskPanier : CreationPanierTask

    @Test
    fun ouvrirUnPanierDoitFermerTousLesAutresPaniers(){

        val captor = argumentCaptor<Iterable<PanierConfirme>>()

        val panier1 = PanierDTO(id = "01_23", etatPanier = EtatPanier.OUVERT, listeAchat = listOf())
        val panier2 = PanierDTO(id = "02_23", etatPanier = EtatPanier.OUVERT, listeAchat = listOf())


        val paniersOuvert = listOf(panier1, panier2)

        `when`(repoPanier.getPaniersOuvert()).thenReturn(paniersOuvert)

        taskPanier.handle()

        verify(repoPanier, times(1)).savePaniers(captor.capture())
        assertEquals(EtatPanier.FERMER, captor.firstValue.elementAt(0).etatPanier)
        assertEquals(EtatPanier.FERMER, captor.firstValue.elementAt(1).etatPanier)
    }

    @Test
    fun lePanierOuvertDoitConcernerLaSemaineEnCours(){
        val panierCaptor = argumentCaptor<PanierDTO>()
        val numSemaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR).toString()

        taskPanier.handle()

        verify(repoPanier, times(1)).savePanier(panierCaptor.capture())
        assertTrue(panierCaptor.firstValue.id.startsWith(numSemaine))
    }

    @Test
    fun lePanierOuvertDoitConcernerLanneeEnCours(){
        val panierCaptor = argumentCaptor<PanierDTO>()
        val numAnnee = Calendar.getInstance().get(Calendar.YEAR).toString().takeLast(2)

        taskPanier.handle()

        verify(repoPanier, times(1)).savePanier(panierCaptor.capture())
        assertTrue(panierCaptor.firstValue.id.endsWith(numAnnee))
    }

    @Test
    fun lePanierDoitAssocierLaPropositionAuPrixLaPlusBasseAlAchat(){

        val panier = PanierDTO(
            id = "01_23",
            etatPanier = EtatPanier.OUVERT,
            listeAchat = listOf()
        )

        val achat = AchatDTO(
            panier = panier,
            adherent = SocieteDTO(
                id = 2,
                nom = "Best Buy",
                email = "bestbuy@adherent.com",
                role = Role.ADHERENT,
                actif = true
            ),
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            quantite = 300
        )
        panier.listeAchat += achat

        val proposition = PropositionDetail(
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
            prix = BigDecimal(3600)
        )

        val captor = argumentCaptor<Iterable<PanierConfirme>>()

        `when`(repoPanier.getPaniersOuvert()).thenReturn(listOf(panier))
        `when`(repoProposition.getLowestPropositionPrixByPrix("VisPRO")).thenReturn(proposition)

        taskPanier.handle()

        verify(repoPanier, times(1)).savePaniers(captor.capture())

        assertEquals(proposition.societe, captor.firstValue.elementAt(0).listeAchat[0].fournisseur)
        assertEquals(proposition.prix, captor.firstValue.elementAt(0).listeAchat[0].prixUnitaire)
    }

    @Test
    fun unAchatConfirmeDoitConserverLesProprietesDelAchatAvantLaFermetureDuPanier(){
        val panier = PanierDTO(
            id = "01_23",
            etatPanier = EtatPanier.OUVERT,
            listeAchat = listOf()
        )

        val achat = AchatDTO(
            panier = panier,
            adherent = SocieteDTO(
                id = 2,
                nom = "Best Buy",
                email = "bestbuy@adherent.com",
                role = Role.ADHERENT,
                actif = true
            ),
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            quantite = 300
        )
        panier.listeAchat += achat

        val proposition = PropositionDetail(
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
            prix = BigDecimal(3600)
        )

        val captor = argumentCaptor<Iterable<PanierConfirme>>()

        `when`(repoPanier.getPaniersOuvert()).thenReturn(listOf(panier))
        `when`(repoProposition.getLowestPropositionPrixByPrix("VisPRO")).thenReturn(proposition)

        taskPanier.handle()

        verify(repoPanier, times(1)).savePaniers(captor.capture())

        assertEquals(achat.adherent, captor.firstValue.elementAt(0).listeAchat[0].adherent)
        assertEquals(achat.produit, captor.firstValue.elementAt(0).listeAchat[0].produit)
        assertEquals(achat.quantite, captor.firstValue.elementAt(0).listeAchat[0].quantite)
    }
}