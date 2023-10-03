package com.raminagrobis.centraleachat.integration.connexion

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nhaarman.mockitokotlin2.doThrow
import com.raminagrobis.centraleachat.app.controller.connexion.ConnexionController
import com.raminagrobis.centraleachat.app.controller.connexion.ConnexionController.ConnexionForm
import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenUtil
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.exception.UserNotFoundException
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.domain.connexion.port.SessionPort
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.json.JacksonTester
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(MockitoExtension::class)
class ConnexionControllerTest {

    private val ip = "192.0.0.1"
    private lateinit var mvc : MockMvc

    @Mock
    private lateinit var utilisateurRepo : IUtilisateurRepo
    private lateinit var jwtTokenUtil: JWTTokenUtil
    @Mock
    private lateinit var sessionRepo : SessionPort

    private lateinit var connexionUtilisateur: ConnexionUtilisateur
    private lateinit var controller : ConnexionController

    private lateinit var jsonForm : JacksonTester<ConnexionForm>
    private lateinit var jsonReturn : JacksonTester<Map<String,String>>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper().registerKotlinModule())

        connexionUtilisateur = ConnexionUtilisateur(utilisateurRepo,jwtTokenUtil,sessionRepo)
        controller = ConnexionController(connexionUtilisateur,jwtTokenUtil)
    }

    @Test
    fun uneConnexionReussie(){
        val email = "test@test.fr"
        val mdp = "JeSuisUnTest"


        val formData = ConnexionForm(email,mdp,true)
        `when`(utilisateurRepo.findAdminByEmail(email)).thenReturn(Utilisateur(email,"\$2y\$10\$.D5bYVqfWZ1SPbJcYzobEeQsC3Ss97m/sVtu9npWDqgxpY66537PK",Role.ADMIN))


        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        //assertEquals(jsonReturn.write(resultat).json, response.contentAsString)
    }

    @Test
    fun uneConnexionDunAdherentRateACauseDunEmailIntrouvable(){
        val email = "mauvaisEmail@test.fr"
        val mdp = "testRate"

        val formData = ConnexionForm(email,mdp,false)

        `when`(connexionUtilisateur.handle(email,mdp,ip,false)).doThrow(UserNotFoundException(email))

        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.status)
    }

    @Test
    fun uneConnexionRateDunAdminACauseDunEmailIntrouvable(){
        val email = "mauvaisEmail@test.fr"
        val mdp = "testRate"

        val formData = ConnexionForm(email,mdp,true)

        `when`(connexionUtilisateur.handle(email,mdp,ip,true)).doThrow(UserNotFoundException(email))

        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.status)
    }

    @Test
    fun uneConnexionRateDunUtilisateurACauseDunMauvaisMotDePasse(){
        val email = "bonEmail@test.fr"
        val mdp = "mauvaisMDP"

        val formData = ConnexionForm(email,mdp,true)

        `when`(connexionUtilisateur.handle(email,mdp,ip,true)).doThrow(BadPasswordException())

        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.status)
    }

}