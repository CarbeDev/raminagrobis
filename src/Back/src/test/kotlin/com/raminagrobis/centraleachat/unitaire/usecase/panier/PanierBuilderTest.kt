package com.raminagrobis.centraleachat.unitaire.usecase.panier

import com.raminagrobis.centraleachat.domain.commande.builder.PanierBuilder
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PanierBuilderTest {

    lateinit var panier: PanierDTO

    @BeforeAll
    fun setup(){
        val jour = Calendar.getInstance()
        jour.set(2023,2,31)

        panier = PanierBuilder().build(jour)
    }

    @Test
    fun lIdDuPanierDoitFaireExactement5Charactere(){
        assertEquals(5,panier.id.length)
    }

    @Test
    fun lIdDuPanierDoitEtreLeNumeroDeLaSemaineEtDeLannee(){
        assertEquals("13_23", panier.id)
    }

    @Test
    fun unPanierCreeDoitEtreOuvert(){
        assertEquals(EtatPanier.OUVERT,panier.etatPanier)
    }
}