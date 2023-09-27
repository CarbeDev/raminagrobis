package com.raminagrobis.centraleachat.usecase.connexion

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.exception.UserNotFoundException
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import org.junit.jupiter.api.Assertions.assertThrows
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

    private val ip = "192.0.0.1"
    @Mock
    private lateinit var jwt :IJWTTokenUtil

    @Mock
    private lateinit var repo :IUtilisateurRepo

    @InjectMocks
    private lateinit var useCase : ConnexionUtilisateur

    private val email = "test@test.fr"
    private val mdp = "test"
    val mdpHash = BCrypt.hashpw(mdp,BCrypt.gensalt())

    @Test
    fun leRepoAdminDoitEtreAppeleLorsqueUnAdminEssaieDeSeConnecter(){
        val admin = Utilisateur(
            email = email,
            motDePasse = mdpHash,
            role = Role.ADMIN
        )
        `when`(repo.findAdminByEmail(email)).doReturn(admin)

        useCase.handle(email,mdp,ip,admin = true)
        verify(repo, times(1)).findAdminByEmail(email)
    }

    @Test
    fun leRepoSocieteDoitEtreAppeleLorsqueUneSocieteEssaieDeSeConnecter(){
        val societe = Utilisateur(
            email = email,
            motDePasse = mdpHash,
            role = Role.FOURNISSEUR)

        `when`(repo.findSocieteByEmail(email)).doReturn(societe)

        useCase.handle(email,mdp,ip,admin = false)
        verify(repo, times(1)).findSocieteByEmail(email)
    }

    @Test
    fun uneTentativeDeConnexionAvecUnEmailInconnuRenvoieUneException(){
        assertThrows(UserNotFoundException::class.java){
            useCase.handle(email,mdp,ip,true)
        }
    }

    @Test
    fun uneConnexionReussiDoitRenvoyeUnTokenJWT(){
        val captor = argumentCaptor<UserDetails>()
        val societe = Utilisateur(email = email, motDePasse = mdpHash, role = Role.ADHERENT)
        `when`(repo.findSocieteByEmail(email)).doReturn(societe)

        useCase.handle(email,mdp,ip,false)
        verify(jwt, times(1)).generateToken(captor.capture())
    }

    @Test
    fun uneTentativeDeConnexionAvecUnMauvaisMotDePasseRevoieUneException(){
        val societe = Utilisateur(email = email, motDePasse = mdpHash, role = Role.ADHERENT)
        `when`(repo.findSocieteByEmail(email)).doReturn(societe)
        val mauvaisMdp = "tes"

        assertThrows(BadPasswordException()::class.java){
            useCase.handle(email, mauvaisMdp, ip,false)
        }
    }
}