package com.raminagrobis.centraleachat.panier

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.config.CreationPanierTask
import com.raminagrobis.centraleachat.domain.commande.model.Etat
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
class CreationPanierTaskTest {

    @Mock
    private lateinit var repoPanier : IPanierRepo

    @InjectMocks
    private lateinit var taskPanier : CreationPanierTask

    @Test
    fun ouvrirUnPanierDoitFermerTousLesAutresPaniers(){

        val panier1 = Panier(id = "01_23", etat = Etat.OUVERT)
        val panier2 = Panier(id = "02_23", etat = Etat.OUVERT)


        val paniersOuvert = listOf(panier1, panier2)

        `when`(repoPanier.getPaniersOuvert()).thenReturn(paniersOuvert)

        taskPanier.handle()

        assertEquals(Etat.FERMER, paniersOuvert[0].etat)
        verify(repoPanier, times(1)).savePanier(panier1)

        assertEquals(Etat.FERMER, paniersOuvert[1].etat)
        verify(repoPanier, times(1)).savePanier(panier2)
    }

    @Test
    fun lePanierOuvertDoitConcernerLaSemaineEnCours(){
        val panierCaptor = argumentCaptor<Panier>()
        val numSemaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR).toString()

        taskPanier.handle()

        verify(repoPanier, times(1)).savePanier(panierCaptor.capture())
        assertTrue(panierCaptor.firstValue.id.contains(numSemaine))
    }
}