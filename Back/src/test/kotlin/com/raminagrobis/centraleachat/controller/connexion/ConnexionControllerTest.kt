package com.raminagrobis.centraleachat.controller.connexion

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.raminagrobis.centraleachat.app.controller.connexion.ConnexionController
import com.raminagrobis.centraleachat.app.controller.connexion.ConnexionController.*
import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.exception.UserNotFoundException
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
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

    private lateinit var mvc : MockMvc

    @Mock
    private lateinit var connexionUtilisateur: ConnexionUtilisateur

    @Mock
    private lateinit var jwtTokenUtil: JWTTokenUtil

    @InjectMocks
    private lateinit var controller : ConnexionController

    private lateinit var jsonForm : JacksonTester<ConnexionForm>
    private lateinit var jsonReturn : JacksonTester<Map<String,String>>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper().registerKotlinModule())
    }

    @Test
    fun uneConnexionReussie(){
        val email = "test@test.fr"
        val mdp = "JeSuisUnTest"

        val formData = ConnexionForm(email,mdp,true)

        val token= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiaWF0IjoxNjg0MTQyNzA1LCJleHAiOjE2ODQxNjA3MDV9.bLY2HghRcoCxxOw-LMFZLJm4qxx5aBvIOPiK-6G7KgM"
        val issuedAt = "Mon May 15 11:25:05 CEST 2023"
        val expiration = "Mon May 15 16:25:05 CEST 2023"

        val resultat = mapOf(
            Pair("Token",token),
            Pair("Issued at", issuedAt),
            Pair("Expire", expiration)
        )

        `when`(connexionUtilisateur.handle(email,mdp, true)).doReturn(token)
        `when`(jwtTokenUtil.getIssuedAt(token)).doReturn(issuedAt)
        `when`(jwtTokenUtil.getExpiration(token)).doReturn(expiration)

        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonReturn.write(resultat).json, response.contentAsString)
    }

    @Test
    fun uneConnexionDunAdherentRateACauseDunEmailIntrouvable(){
        val email = "mauvaisEmail@test.fr"
        val mdp = "testRate"

        val formData = ConnexionForm(email,mdp,false)

        `when`(connexionUtilisateur.handle(email,mdp,false)).doThrow(UserNotFoundException(email))

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

        `when`(connexionUtilisateur.handle(email,mdp,true)).doThrow(UserNotFoundException(email))

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

        `when`(connexionUtilisateur.handle(email,mdp,true)).doThrow(BadPasswordException())

        val response = mvc.perform(
            post("/connexion").contentType(MediaType.APPLICATION_JSON).content(
                jsonForm.write(formData).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.status)
    }

}