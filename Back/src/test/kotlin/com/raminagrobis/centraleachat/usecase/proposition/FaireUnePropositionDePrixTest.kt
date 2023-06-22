package com.raminagrobis.centraleachat.usecase.proposition

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class FaireUnePropositionDePrixTest {

    @Mock
    private lateinit var propositionRepo : IPropositionRepo

    @InjectMocks
    private lateinit var usecase : FaireUnePropositionDePrix

    @Test
    fun unePropositionAvecUnPrixSuperieurA0EtAvecUneSocieteFournisseurDoitEtreSauvegarder(){

        val proposition = PropositionDTO(
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
            produit = ProduitDTO(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            prix = BigDecimal(3200)
        )

        val propositionCaptor = argumentCaptor<PropositionDTO>()

        usecase.handle(proposition)

        verify(propositionRepo, times(1)).saveProposition(propositionCaptor.capture())

        assertEquals(BigDecimal(3200),propositionCaptor.firstValue.prix)
    }

    @Test
    fun unePropositionAvecUnPrixInferieurOuEgalA0DoitEnvoyeUneException(){
        val proposition = PropositionDTO(
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
            produit = ProduitDTO(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            prix = BigDecimal(0)
        )

        assertThrows(PriceTooLowException::class.java){
            usecase.handle(proposition)
        }
    }

    @Test
    fun unePropositionAvecUneSocieteAdherenteDoitRenvoyerUneErreur(){
        val proposition = PropositionDTO(
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.ADHERENT,
                actif = false
            ),
            produit = ProduitDTO(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            prix = BigDecimal(3200)
        )
        assertThrows(IncorrectRoleSocieteException::class.java){
            usecase.handle(proposition)
        }
    }
}