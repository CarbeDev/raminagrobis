package com.raminagrobis.centraleachat.controller.proposition

import com.fasterxml.jackson.databind.ObjectMapper
import com.raminagrobis.centraleachat.app.controller.FournisseurPropositionController
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.SupprimerUnePropositionDePrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.json.JacksonTester
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(MockitoExtension::class)
class FournisseurPropositionControllerTest {

    private lateinit var mvc : MockMvc

    @Mock
    private lateinit var faireUnePropositionDePrix: FaireUnePropositionDePrix

    @Mock
    private lateinit var supprimerUnePropositionDePrix: SupprimerUnePropositionDePrix

    @InjectMocks
    private lateinit var controller : FournisseurPropositionController

    private lateinit var jsonProposition : JacksonTester<Proposition>

    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }


    @Test
    fun unePropositionDePrixEstEnregistre(){

        val response = mvc.perform(
            post("/fournisseur/proposition/add").contentType(MediaType.APPLICATION_JSON).content(
                jsonProposition.write(Proposition()).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(),response.status,)
    }

    @Test
    fun unePropositionDePrixEstSupprimer(){

        val response = mvc.perform(
            delete("/fournisseur/proposition/delete").contentType(MediaType.APPLICATION_JSON).content(
                jsonProposition.write(Proposition()).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(),response.status)
    }

}