package com.raminagrobis.centraleachat.controller.demande

import com.fasterxml.jackson.databind.ObjectMapper
import com.raminagrobis.centraleachat.app.controller.demande.DemandeController
import com.raminagrobis.centraleachat.domain.administration.model.Categorie
import com.raminagrobis.centraleachat.domain.demande.model.Demande
import com.raminagrobis.centraleachat.domain.demande.usecase.FaireDemande
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class DemandeControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var faireDemande: FaireDemande

    @InjectMocks
    private lateinit var controller : DemandeController

    private lateinit var jsonDemande : JacksonTester<Demande>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun uneDemandeEstFaite(){
        val demande = Demande(
            id = 1,
            nom = "Apple TrackPad",
            description = "Trop cher",
            categorie = Categorie(
                id = 1,
                libelle = "Souris"
            ),
        )

        val response = mvc.perform(
            post("/demande").contentType(MediaType.APPLICATION_JSON).content(
                jsonDemande.write(demande).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(),response.status)
    }
}