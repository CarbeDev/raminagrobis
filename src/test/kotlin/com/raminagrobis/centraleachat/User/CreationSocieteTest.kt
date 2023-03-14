package com.raminagrobis.centraleachat.User

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.societe.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.societe.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.societe.model.Role
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import com.raminagrobis.centraleachat.domain.societe.usecase.CreationSociete
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
    private lateinit var userRepo : UserRepoInterface
    @InjectMocks
    private lateinit var creationSociete: CreationSociete

    val email = "test@raminagrobis.com"
    val nom = "nom"
    val role = Role.ADHERENT

    @Test
    fun createAUserShouldSaveItOnceWithCorrectProperties(){

        val societeCaptor = argumentCaptor<Societe>()

        `when`(userRepo.isEmailUnique(email)).thenReturn(true)
        creationSociete.handle(email, nom, role)

        verify(userRepo, times(1)).saveUser(societeCaptor.capture())
        assertEquals(email,societeCaptor.firstValue.email)
        assertEquals(nom,societeCaptor.firstValue.nom)
        assertEquals(role,societeCaptor.firstValue.role)
        assertTrue(societeCaptor.firstValue.actif)
    }

    @Test
    fun createAUserWithNonUniqueEmailMustThrowException(){
        `when`(userRepo.isEmailUnique(email)).thenReturn(false)

        assertThrows(EmailAlreadyUseException::class.java) {
            creationSociete.handle(email, nom, role)
        }

        verify(userRepo, times(0)).saveUser(com.nhaarman.mockitokotlin2.any())
    }

}