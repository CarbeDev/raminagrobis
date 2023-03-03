package com.raminagrobis.centraleachat.User

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.user.adapter.UserRepoInterface
import com.raminagrobis.centraleachat.domain.user.model.Role
import com.raminagrobis.centraleachat.domain.user.model.User
import com.raminagrobis.centraleachat.domain.user.usecase.DeleteUser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DeleteUserTest {

    @Mock
    lateinit var userRepo: UserRepoInterface

    @InjectMocks
    lateinit var deleteUser: DeleteUser

    lateinit var user: User

    @BeforeEach
    fun setup(){
        user = User(
            email = "test@raminagrobis.com",
            prenom = "prenom",
            nom = "nom",
            role = Role.ADHERENT,
            actif = true
        )
    }

    @Test
    fun aUserWithoutCommandeMustBeDeleted(){

        val userArgumentCaptor = argumentCaptor<User>()


        `when`(userRepo.getNbCommandeByUser(user)).thenReturn(0)
        deleteUser.handle(user)

        verify(userRepo, times(1)).deleteUser(userArgumentCaptor.capture())
    }

    @Test
    fun aUserWithCommandeMustBeDesactive(){
        val userArgumentCaptor = argumentCaptor<User>()

        `when`(userRepo.isEmailUnique(user.email)).thenReturn(true)
        `when`(userRepo.getNbCommandeByUser(user)).thenReturn(1)
        deleteUser.handle(user)

        verify(userRepo, times(1)).saveUser(userArgumentCaptor.capture())
        Assertions.assertFalse(userArgumentCaptor.firstValue.actif)
    }


}