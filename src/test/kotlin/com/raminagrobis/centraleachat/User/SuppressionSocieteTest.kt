package com.raminagrobis.centraleachat.User

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.societe.adapter.SocieteRepoInterface
import com.raminagrobis.centraleachat.domain.societe.model.Role
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import com.raminagrobis.centraleachat.domain.societe.usecase.SuppressionSociete
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SuppressionSocieteTest {

    @Mock
    lateinit var userRepo: SocieteRepoInterface

    @InjectMocks
    lateinit var suppressionSociete: SuppressionSociete

    lateinit var societe: Societe

    @BeforeEach
    fun setup(){
        societe = Societe(
            email = "test@raminagrobis.com",
            nom = "nom",
            role = Role.ADHERENT,
            actif = true
        )
    }

    @Test
    fun aUserWithoutCommandeMustBeDeleted(){

        `when`(userRepo.getNbCommandeByUser(societe)).thenReturn(0)
        suppressionSociete.handle(societe)

        verify(userRepo, times(1)).deleteUser(societe)
    }

    @Test
    fun aUserWithCommandeMustBeDesactive(){
        val societeArgumentCaptor = argumentCaptor<Societe>()

        `when`(userRepo.isEmailUnique(societe.email.toString())).thenReturn(true)
        `when`(userRepo.getNbCommandeByUser(societe)).thenReturn(1)
        suppressionSociete.handle(societe)

        verify(userRepo, times(1)).saveSociete(societeArgumentCaptor.capture())
        societeArgumentCaptor.firstValue.actif?.let { Assertions.assertFalse(it) }
    }


}