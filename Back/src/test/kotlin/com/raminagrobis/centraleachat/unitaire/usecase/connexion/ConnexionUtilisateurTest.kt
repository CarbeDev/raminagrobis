package com.raminagrobis.centraleachat.unitaire.usecase.connexion

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.dto.Session
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.domain.connexion.port.SessionPort
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCrypt

@ExtendWith(MockitoExtension::class)
class ConnexionUtilisateurTest {

    private val ip = "192.0.0.1"
    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkByYW1pbmFncm9iaXMuY29tIiwiaWF0IjoxNjk1NzM2OTc0LCJleHAiOjE2OTU3NTQ5NzR9.cVwyOzBLlQZuOMTI3oPrIPo6-fP44NB8GFniY1-SUi0"
    @Mock
    private lateinit var jwt :IJWTTokenUtil
    @Mock
    private lateinit var utilisateurRepo :IUtilisateurRepo
    @Mock
    private lateinit var sessionRepo : SessionPort
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
        `when`(utilisateurRepo.findAdminByEmail(email)).doReturn(admin)
        `when`(jwt.generateToken(admin.toSpringUser())).doReturn(token)

        useCase.handle(email,mdp,ip,admin = true)

        verify(utilisateurRepo, times(1)).findAdminByEmail(email)
    }

    @Test
    fun leRepoSocieteDoitEtreAppeleLorsqueUneSocieteEssaieDeSeConnecter(){
        val societe = Utilisateur(
            email = email,
            motDePasse = mdpHash,
            role = Role.FOURNISSEUR)

        `when`(utilisateurRepo.findSocieteByEmail(email)).doReturn(societe)
        `when`(jwt.generateToken(societe.toSpringUser())).doReturn(token)

        useCase.handle(email,mdp,ip,admin = false)
        verify(utilisateurRepo, times(1)).findSocieteByEmail(email)
    }

    @Test
    fun uneConnexionReussieRenvoiUnToken(){
        val societe = Utilisateur(
            email = email,
            motDePasse = mdpHash,
            role = Role.FOURNISSEUR)


        `when`(utilisateurRepo.findSocieteByEmail(email)).doReturn(societe)
        `when`(jwt.generateToken(societe.toSpringUser())).doReturn(token)

        assertEquals(token,useCase.handle(email,mdp,ip,admin = false))
    }

    @Test
    fun uneConnexionReussieCreerUneSession(){
        val captor = argumentCaptor<Session>()
        val societe = Utilisateur(
            email = email,
            motDePasse = mdpHash,
            role = Role.FOURNISSEUR)

        val token = "token"

        `when`(utilisateurRepo.findSocieteByEmail(email)).doReturn(societe)
        `when`(jwt.generateToken(societe.toSpringUser())).doReturn(token)

        useCase.handle(email,mdp,ip,admin = false)

        verify(sessionRepo, times(1)).creerSession(captor.capture())
        assertEquals(ip,captor.firstValue.ip)
        assertEquals(token, captor.firstValue.jwt)
    }

    @Test
    fun uneTentativeDeConnexionAvecUnEmailInconnuRenvoieUneException(){
        assertThrows(NullPointerException::class.java){
            useCase.handle(email,mdp,ip,true)
        }
    }

    @Test
    fun uneTentativeDeConnexionAvecUnMauvaisMotDePasseRevoieUneException(){
        val societe = Utilisateur(email = email, motDePasse = mdpHash, role = Role.ADHERENT)
        `when`(utilisateurRepo.findSocieteByEmail(email)).doReturn(societe)
        val mauvaisMdp = "tes"

        assertThrows(BadPasswordException()::class.java){
            useCase.handle(email, mauvaisMdp, ip,false)
        }
    }
}