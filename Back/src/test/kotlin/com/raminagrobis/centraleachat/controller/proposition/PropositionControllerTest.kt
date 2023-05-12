package com.raminagrobis.centraleachat.controller.proposition

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.app.controller.PropositionController
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.model.PropositionKey
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.RecupererPropositions
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class PropositionControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var recupererPropositions: RecupererPropositions

    @InjectMocks
    private lateinit var  controller: PropositionController

    private lateinit var jsonPropositions : JacksonTester<Iterable<Proposition>>

    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun DesPropositionsPeuventEtreRecupererAPartirDeLeurFournisseur(){

        val resultat = listOf(
            Proposition(
                propositionKey = PropositionKey("LG502", 1),
                prix = BigDecimal(30)
            ),
            Proposition(
                propositionKey = PropositionKey("LStreamCam", 1),
                prix = BigDecimal(80)
            )
        )

        `when`(recupererPropositions.handle(1)).doReturn(resultat)

        val response = mvc.perform(
            get("/propositions/fournisseur/1").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonPropositions.write(resultat).json, response.contentAsString)
    }

    @Test
    fun DesPropositionsPeuventEtreRecupererAPartirDeLeurProduit(){

        val resultat = listOf(
            Proposition(
                propositionKey = PropositionKey("LG502", 1),
                prix = BigDecimal(30)
            ),
            Proposition(
                propositionKey = PropositionKey("LG502", 2),
                prix = BigDecimal(40)
            )
        )

        `when`(recupererPropositions.handle("LG502")).doReturn(resultat)

        val response = mvc.perform(
            get("/propositions/produit/LG502").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonPropositions.write(resultat).json, response.contentAsString)
    }
}