package com.raminagrobis.centraleachat.Societe

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.administration.usecase.CreerSociete
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CreerSocieteTest{

    @Mock
    private lateinit var societeRepo : ISocieteRepo
    @InjectMocks
    private lateinit var creerSociete: CreerSociete

    val email = "test@raminagrobis.com"
    val nom = "nom"
    val role = Role.ADHERENT

    @Test
    fun creerUnUtilisateurDoitLeSauvegarderAvecLesBonnesProprietes(){

        val societeCaptor = argumentCaptor<Societe>()

        `when`(societeRepo.isEmailUnique(email)).thenReturn(true)
        creerSociete.handle(email, nom, role)

        verify(societeRepo, times(1)).saveSociete(societeCaptor.capture())
        assertEquals(email,societeCaptor.firstValue.email)
        assertEquals(nom,societeCaptor.firstValue.nom)
        assertEquals(role,societeCaptor.firstValue.role)
        societeCaptor.firstValue.actif?.let { assertTrue(it) }
    }

    @Test
    fun creerUnUtilisateurAvecUnMailDejaUtiliseDoitEnvoyeUneException(){
        `when`(societeRepo.isEmailUnique(email)).thenReturn(false)

        assertThrows(EmailAlreadyUseException::class.java) {
            creerSociete.handle(email, nom, role)
        }

        verify(societeRepo, times(0)).saveSociete(com.nhaarman.mockitokotlin2.any())
    }

}