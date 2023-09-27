package com.raminagrobis.centraleachat.unitaire.usecase.proposition

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
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
class FaireUnePropositionDePrixTest {

    @Mock
    private lateinit var propositionRepo : IPropositionRepo
    @Mock
    private lateinit var societeRepo : ISocieteRepo

    @InjectMocks
    private lateinit var usecase : FaireUnePropositionDePrix

    @Test
    fun unePropositionAvecUnPrixSuperieurA0EtAvecUneSocieteFournisseurDoitEtreSauvegarder(){

        `when`(societeRepo.findSocieteByID(1)).thenReturn(DetailSociete(
            id = 1,
            nom = "Fournisseur1",
            email = "fournisseur1@email.fr",
            role = Role.FOURNISSEUR,
            actif = false,
            historique = listOf<AchatEntity>(),
            dateInscription = Date()

        ))


        val proposition = PropositionDTO(
            idSociete = 1,
            refProduit = "VisPRO",
            prix = BigDecimal(3200)
        )

        val propositionCaptor = argumentCaptor<PropositionDTO>()

        usecase.handle(proposition)

        verify(propositionRepo, times(1)).saveProposition(propositionCaptor.capture())

        assertEquals(proposition,propositionCaptor.firstValue)
    }

    @Test
    fun unePropositionAvecUnPrixInferieurOuEgalA0DoitEnvoyeUneException(){

        `when`(societeRepo.findSocieteByID(1)).thenReturn(DetailSociete(
            id = 1,
            nom = "Fournisseur1",
            email = "fournisseur1@email.fr",
            role = Role.FOURNISSEUR,
            actif = false,
            historique = listOf<AchatEntity>(),
            dateInscription = Date()

        ))

        val proposition = PropositionDTO(
            idSociete = 1,
            refProduit = "VisPRO",
            prix = BigDecimal(0)
        )

        assertThrows(PriceTooLowException::class.java){
            usecase.handle(proposition)
        }
    }

    @Test
    fun unePropositionAvecUneSocieteAdherenteDoitRenvoyerUneErreur(){
        val proposition = PropositionDTO(
            idSociete = 2,
            refProduit = "VisPRO",
            prix = BigDecimal(3200)
        )

        `when`(societeRepo.findSocieteByID(2)).thenReturn(DetailSociete(
            id = 2,
            nom = "Lidl",
            email = "lidl@adherent.com",
            role = Role.ADHERENT,
            actif = false,
            dateInscription = Date(),
            historique = listOf()
        ))

        assertThrows(IncorrectRoleSocieteException::class.java){
            usecase.handle(proposition)
        }
    }
}