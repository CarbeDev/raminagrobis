package com.raminagrobis.centraleachat.usecase.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class EffectuerAchatTest {

    @Mock
    private lateinit var repoAchat: IAchatRepo
    @Mock
    private lateinit var repoSociete : ISocieteRepo
    @Mock
    private lateinit var repoPanier : IPanierRepo

    @InjectMocks
    private lateinit var useCase : EffectuerAchat

    private lateinit var achat : AchatMin

    @BeforeEach
    fun setup(){
        achat = AchatMin(
            idAdherent = 1,
            refProduit = "Test",
            idPanier = "23-23",
            quantite = 2000
        )
    }


    @Test
    fun unAchatQuiEstEffectueParUnFournisseurDoitEnvoiUneException(){

        `when`(repoSociete.findSocieteByID(1)).thenReturn(
            DetailSociete(
                id = 1,
                nom = "Free agent",
                email = "Freeagent@Adherent.com",
                role = Role.FOURNISSEUR,
                actif = true,
                dateInscription = Date()
            )
        )

        `when`(repoPanier.findById("23-23")).thenReturn(
            PanierDTO(
                id = "23-23",
                etatPanier = EtatPanier.OUVERT,
                listeAchat = listOf()
            )
        )

        assertThrows(IncorrectRoleSocieteException::class.java){
            useCase.handle(achat)
        }

    }

    @Test
    fun unAchatQuiConcerneUnPanierFermeEnvoiUneException(){

        `when`(repoSociete.findSocieteByID(1)).thenReturn(
            DetailSociete(
                id = 1,
                nom = "Free agent",
                email = "Freeagent@Adherent.com",
                role = Role.ADHERENT,
                actif = true,
                dateInscription = Date()
            )
        )

        `when`(repoPanier.findById("23-23")).thenReturn(
            PanierDTO(
                id = "23-23",
                etatPanier = EtatPanier.FERMER,
                listeAchat = listOf()
            )
        )

        assertThrows(CantAddAchatException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatDUnAdherentDansUnPanierFermeDoitEtreSauvegarder(){

        `when`(repoSociete.findSocieteByID(1)).thenReturn(
            DetailSociete(
                id = 1,
                nom = "Free agent",
                email = "Freeagent@Adherent.com",
                role = Role.ADHERENT,
                actif = true,
                dateInscription = Date()
            )
        )

        `when`(repoPanier.findById("23-23")).thenReturn(
            PanierDTO(
                id = "23-23",
                etatPanier = EtatPanier.OUVERT,
                listeAchat = listOf()
            )
        )

        useCase.handle(achat)

        verify(repoAchat, times(1)).saveAchat(achat)
    }
}