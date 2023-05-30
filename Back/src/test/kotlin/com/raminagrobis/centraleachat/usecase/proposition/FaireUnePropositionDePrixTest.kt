package com.raminagrobis.centraleachat.usecase.proposition

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import org.junit.jupiter.api.Assertions
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
        val societe = Societe(role = Role.FOURNISSEUR)
        val prix = BigDecimal(100)
        val proposition = Proposition(prix = prix, societe = societe)

        val propositionCaptor = argumentCaptor<Proposition>()

        usecase.handle(proposition)

        verify(propositionRepo, times(1)).saveProposition(propositionCaptor.capture())
        Assertions.assertEquals(BigDecimal(100),propositionCaptor.firstValue.prix)
    }

    @Test
    fun unePropositionAvecUnPrixInferieurOuEgalA0DoitEnvoyeUneException(){
        val societe = Societe(role = Role.FOURNISSEUR)
        val proposition = Proposition(prix = BigDecimal(0), societe = societe)

        Assertions.assertThrows(PriceTooLowException::class.java){
            usecase.handle(proposition)
        }
    }

    @Test
    fun unePropositionAvecUneSocieteAdherenteDoitRenvoyerUneErreur(){
        val societe = Societe(role = Role.ADHERENT)
        val prix = BigDecimal(100)

        val proposition = Proposition(prix = prix, societe = societe)

        Assertions.assertThrows(IncorrectRoleSocieteException::class.java){
            usecase.handle(proposition)
        }
    }
}