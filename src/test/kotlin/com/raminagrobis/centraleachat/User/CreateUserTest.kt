package com.raminagrobis.centraleachat.User

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.user.model.Role
import com.raminagrobis.centraleachat.domain.user.model.User
import com.raminagrobis.centraleachat.domain.user.usecase.CreateUser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CreateUserTest{

    @Mock
    private lateinit var userRepo : UserRepoInterface
    @InjectMocks
    private lateinit var createUser: CreateUser

    val email = "test@raminagrobis.com"
    val prenom = "prenom"
    val nom = "nom"
    val role = Role.ADHERENT

    @Test
    fun createAUserShouldSaveItOnceWithCorrectProperties(){

        val userCaptor = argumentCaptor<User>()

        `when`(userRepo.isEmailUnique(email)).thenReturn(true)
        createUser.createUser(email,prenom, nom, role)

        verify(userRepo, times(1)).saveUser(userCaptor.capture())
        assertEquals(email,userCaptor.firstValue.email)
        assertEquals(prenom,userCaptor.firstValue.prenom)
        assertEquals(nom,userCaptor.firstValue.nom)
        assertEquals(role,userCaptor.firstValue.role)
        assertTrue(userCaptor.firstValue.actif)
    }

    @Test
    fun createAUserWithNonUniqueEmailMustThrowException(){
        `when`(userRepo.isEmailUnique(email)).thenReturn(false)

        assertThrows(EmailAlreadyUseException::class.java) {
            createUser.createUser(
                email,
                prenom,
                nom,
                role
            )
        }

        verify(userRepo, times(0)).saveUser(com.nhaarman.mockitokotlin2.any())
    }

}