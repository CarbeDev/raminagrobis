package com.raminagrobis.centraleachat.panier

import com.raminagrobis.centraleachat.domain.commande.builder.PanierBuilder
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PanierBuilderTest {

    lateinit var panier: Panier

    @BeforeAll
    fun setup(){
        val jour = Calendar.getInstance()
        jour.set(2023,2,31)

        panier = PanierBuilder().build(jour)
    }

    @Test
    fun LIdDuPanierDoitFaireExactement5Charactere(){
        Assertions.assertEquals(5,panier.id.length)
    }

    @Test
    fun LIdDuPanierDoitEtreLeNumeroDeLaSemaineEtDeLannee(){
        Assertions.assertEquals("13_23", panier.id)
    }
}