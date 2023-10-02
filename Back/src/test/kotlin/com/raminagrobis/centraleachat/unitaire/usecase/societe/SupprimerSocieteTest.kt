package com.raminagrobis.centraleachat.unitaire.usecase.societe

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.usecase.SupprimerSociete
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class SupprimerSocieteTest {

    val email = "test@raminagrobis.com"
    val nom = "nom"

    @Mock
    lateinit var societeRepo: ISocieteRepo

    @Mock
    lateinit var achatRepo: IAchatRepo

    @InjectMocks
    lateinit var supprimerSociete: SupprimerSociete

    lateinit var societe: DetailSociete

    @BeforeEach
    fun setup(){

        societe = DetailSociete(
            id = 1,
            email = email,
            nom = nom,
            role = Role.ADHERENT,
            actif = true,
            dateInscription = Date()
        )

        `when`(societeRepo.findSocieteByID(1)).thenReturn(societe)
    }

    @Test
    fun uneSocieteSansCommandeDoitEtreSupprime(){
        `when`(achatRepo.getNbAchatBySociete(societe)).thenReturn(0)
        supprimerSociete.handle(1)

        verify(societeRepo, times(1)).deleteSociete(1)
    }

    @Test
    fun uneSocieteAvecAuMoinsUneCommandeDoitEtreDesactive(){
        val societeArgumentCaptor = argumentCaptor<DetailSociete>()
        `when`(achatRepo.getNbAchatBySociete(societe)).thenReturn(1)

        supprimerSociete.handle(1)

        verify(societeRepo, times(1)).saveSociete(societeArgumentCaptor.capture())
        assertFalse(societeArgumentCaptor.firstValue.actif)
    }

    @Test
    fun uneSocieteAvecAuMoinsUneCommandeDoitEtreAnonymiser(){
        val societeArgumentCaptor = argumentCaptor<DetailSociete>()
        `when`(achatRepo.getNbAchatBySociete(societe)).thenReturn(1)

        supprimerSociete.handle(1)

        verify(societeRepo, times(1)).saveSociete(societeArgumentCaptor.capture())
        assertFalse(societeArgumentCaptor.firstValue.nom.contains(nom))
        assertFalse(societeArgumentCaptor.firstValue.email.contains(email))
    }

}