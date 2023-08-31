package com.raminagrobis.centraleachat.controller.proposition

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.app.controller.proposition.FournisseurPropositionController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.SupprimerUnePropositionDePrix
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
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
import java.math.BigDecimal


@ExtendWith(MockitoExtension::class)
class FournisseurPropositionControllerTest {

    private lateinit var mvc : MockMvc

    @Mock
    private lateinit var faireUnePropositionDePrix: FaireUnePropositionDePrix

    @Mock
    private lateinit var supprimerUnePropositionDePrix: SupprimerUnePropositionDePrix

    @InjectMocks
    private lateinit var controller : FournisseurPropositionController

    private lateinit var jsonProposition : JacksonTester<PropositionDTO>
    private lateinit var jsonPropositionKey : JacksonTester<PropositionKey>
    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()

    }


    @Test
    fun unePropositionDePrixEstEnregistre(){

        val proposition = PropositionDTO(
            idSociete = 1,
            refProduit = "VisPRO",
            prix = BigDecimal(3200)
        )

        val response = mvc.perform(
            post("/fournisseur/propositions").contentType(MediaType.APPLICATION_JSON).content(
                jsonProposition.write(proposition).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(),response.status)
        verify(faireUnePropositionDePrix, times(1)).handle(proposition)
    }

    @Test
    fun unePropositionDePrixEstSupprimer(){

        val propositionKey = PropositionKey(
            idSociete = 1,
            reference = "VisPro"
        )

        val response = mvc.perform(
            delete("/fournisseur/propositions").contentType(MediaType.APPLICATION_JSON).content(
                jsonPropositionKey.write(propositionKey).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(),response.status)
        verify(supprimerUnePropositionDePrix, times(1)).handle(any())
    }

}