package com.raminagrobis.centraleachat.Societe

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.administration.usecase.CreationSociete
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CreationSocieteTest{

    @Mock
    private lateinit var societeRepo : ISocieteRepo
    @InjectMocks
    private lateinit var creationSociete: CreationSociete

    val email = "test@raminagrobis.com"
    val nom = "nom"
    val role = Role.ADHERENT

    @Test
    fun createAUserShouldSaveItOnceWithCorrectProperties(){

        val societeCaptor = argumentCaptor<Societe>()

        `when`(societeRepo.isEmailUnique(email)).thenReturn(true)
        creationSociete.handle(email, nom, role)

        verify(societeRepo, times(1)).saveSociete(societeCaptor.capture())
        assertEquals(email,societeCaptor.firstValue.email)
        assertEquals(nom,societeCaptor.firstValue.nom)
        assertEquals(role,societeCaptor.firstValue.role)
        societeCaptor.firstValue.actif?.let { assertTrue(it) }
    }

    @Test
    fun createAUserWithNonUniqueEmailMustThrowException(){
        `when`(societeRepo.isEmailUnique(email)).thenReturn(false)

        assertThrows(EmailAlreadyUseException::class.java) {
            creationSociete.handle(email, nom, role)
        }

        verify(societeRepo, times(0)).saveSociete(com.nhaarman.mockitokotlin2.any())
    }

}