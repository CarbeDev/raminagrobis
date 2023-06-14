package com.raminagrobis.centraleachat.usecase.societe

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.usecase.SupprimerSociete
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SupprimerSocieteTest {

    @Mock
    lateinit var societeRepo: ISocieteRepo

    @InjectMocks
    lateinit var supprimerSociete: SupprimerSociete

    lateinit var societe: DetailSociete

    @BeforeEach
    fun setup(){
        societe = DetailSociete(
            id = 1,
            email = "test@raminagrobis.com",
            nom = "nom",
            role = Role.ADHERENT,
            actif = true,
            historique = listOf()
        )
    }

    @Test
    fun uneSocieteSansCommandeDoitEtreSupprime(){

        `when`(societeRepo.findSocieteByID(1)).thenReturn(societe)

        supprimerSociete.handle(1)

        verify(societeRepo, times(1)).deleteSociete(societe)
    }

    @Test
    fun uneSocieteAvecAuMoinsUneCommandeDoitEtreDesactive(){
        val societeArgumentCaptor = argumentCaptor<DetailSociete>()
        societe.historique = listOf(Achat())

        `when`(societeRepo.findSocieteByID(1)).thenReturn(societe)
        `when`(societeRepo.isEmailUnique(societe.email)).thenReturn(true)

        supprimerSociete.handle(1)

        verify(societeRepo, times(1)).saveSociete(societeArgumentCaptor.capture())
        assertFalse(societeArgumentCaptor.firstValue.actif)
    }



}