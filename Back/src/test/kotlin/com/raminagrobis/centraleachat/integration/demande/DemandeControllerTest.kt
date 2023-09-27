package com.raminagrobis.centraleachat.integration.demande

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.app.controller.demande.DemandeController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.usecase.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class DemandeControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var faireDemande: FaireDemande
    @Mock
    private lateinit var accepterDemande: AccepterDemande
    @Mock
    private lateinit var refuserDemande: RefuserDemande
    @Mock
    private lateinit var recupererDemandeParId: RecupererDemandeParId
    @Mock
    private lateinit var  recupererDemandeParSociete: RecupererDemandeParSociete
    @Mock
    private lateinit var recupererDemandes: RecupererDemandes

    @InjectMocks
    private lateinit var controller : DemandeController

    private lateinit var jsonDemande : JacksonTester<DemandeDTO>
    private lateinit var jsonDemandeGere: JacksonTester<DemandeGere>
    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun uneDemandeEstFaite(){
        val demande = DemandeDTO(
            id = 1,
            nom = "Apple TrackPad",
            description = "Trop cher",
            idCategorie = 1,
            idSociete = 1
        )

        val response = mvc.perform(
            post("/demandes").contentType(MediaType.APPLICATION_JSON).content(
                jsonDemande.write(demande).json
            )
        ).andReturn().response

        verify(faireDemande, times(1)).handle(demande)
        assertEquals(HttpStatus.OK.value(),response.status)
    }

    @Test
    fun uneDemandeEstAccepte(){

        val demandeGere = DemandeGere(
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            idDemande = 1
        )

        val response = mvc.perform(
            post("/admin/demandes").contentType(MediaType.APPLICATION_JSON).content(
                jsonDemandeGere.write(demandeGere).json
            )
        ).andReturn().response

        verify(accepterDemande, times(1)).handle(demandeGere)
        assertEquals(HttpStatus.CREATED.value(), response.status)
    }

    @Test
    fun uneDemandeEstRefuse(){
        val response = mvc.perform(
            patch("/admin/demandes/1")
        ).andReturn().response

        verify(refuserDemande, times(1)).handle(1)
        assertEquals(HttpStatus.OK.value(), response.status)
    }

    @Test
    fun uneDemandeEstRecupere(){
        val reponse = mvc.perform(
            get("/demandes/1")
        ).andReturn().response

        verify(recupererDemandeParId, times(1)).handle(1)
        assertEquals(HttpStatus.OK.value(), reponse.status)
    }

    @Test
    fun desDemandesSontRecupere(){
        val reponse = mvc.perform(
            get("/demandes")
        ).andReturn().response

        verify(recupererDemandes, times(1)).handle()
        assertEquals(HttpStatus.OK.value(),reponse.status)
    }

    @Test
    fun desDemandesSontRecupereSelonLeurSociete(){
        val reponse = mvc.perform(
            get("/demandes/societe/1")
        ).andReturn().response

        verify(recupererDemandeParSociete, times(1)).handle(1)
        assertEquals(HttpStatus.OK.value(),reponse.status)
    }
}