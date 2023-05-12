package com.raminagrobis.centraleachat.usecase.connexion

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCrypt

@ExtendWith(MockitoExtension::class)
class ConnexionUtilisateurTest {

    @Mock
    private lateinit var jwt :IJWTTokenUtil

    @Mock
    private lateinit var repo :IUtilisateurRepo

    @InjectMocks
    private lateinit var useCase : ConnexionUtilisateur

    private val email = "test@test.fr"
    private val mdp = "test"

    @BeforeEach
    fun setup(){
        val mdpHash = BCrypt.hashpw(mdp,BCrypt.gensalt())

        val utilisateurRetourne = Societe(email = email, motDePasse = mdpHash)

        `when`(repo.findSocieteByEmail(email)).thenReturn(utilisateurRetourne)
    }

    @Test
    fun uneConnexionReussiDoitRenvoyeUnTokenJWT(){
        val captor = argumentCaptor<UserDetails>()

        useCase.handle(email,mdp,false)
        verify(jwt, times(1)).generateToken(captor.capture())
    }

    @Test
    fun uneTentativeDeConnexionAvecUnMauvaisMotDePasseRevoieUneException(){
        val mauvaisMdp = "tes"

        Assertions.assertThrows(BadPasswordException()::class.java){
            useCase.handle(email, mauvaisMdp, false)
        }
    }
}