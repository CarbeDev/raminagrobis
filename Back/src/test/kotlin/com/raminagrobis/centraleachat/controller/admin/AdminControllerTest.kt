package com.raminagrobis.centraleachat.controller.admin

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.app.controller.admin.AdminController
import com.raminagrobis.centraleachat.app.controller.admin.SocieteToCreate
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import com.raminagrobis.centraleachat.domain.administration.usecase.RecupererSocietes.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class AdminControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var recupererSociete: RecupererSociete
    @Mock
    private lateinit var recupererSocietes: RecupererSocietes
    @Mock
    private lateinit var creerSociete: CreerSociete
    @Mock
    private lateinit var supprimerSociete: SupprimerSociete
    @Mock
    private lateinit var miseAJourSociete: MiseAJourSociete
    @InjectMocks
    private lateinit var controller : AdminController

    private lateinit var jsonSocieteDTOs: JacksonTester<Iterable<SocieteDTO>>
    private lateinit var jsonSocieteDTO: JacksonTester<SocieteDTO>
    private lateinit var jsonSocieteToCreate: JacksonTester<SocieteToCreate>
    private lateinit var jsonSociete : JacksonTester<Societe>

    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this,ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun uneRecuperationDesSocietes(){

        val societes = listOf(
            SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
            SocieteDTO(
                id = 2,
                nom = "Adherent1",
                email = "Adherent1@email.fr",
                role = Role.ADHERENT,
                actif = true
            )
        )

        `when`(recupererSocietes.handle()).doReturn(societes)

        val response = mvc.perform(
            get("/admin/societes")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(),response.status)
        assertEquals(jsonSocieteDTOs.write(societes).json, response.contentAsString)
    }

    @Test
    fun uneRecuperationDeSociete(){
        val societe = SocieteDTO(
            id = 1,
            nom = "Fournisseur1",
            email = "fournisseur1@email.fr",
            role = Role.FOURNISSEUR,
            actif = false
        )

        `when`(recupererSociete.handle(1)).doReturn(societe)

        val response = mvc.perform(
            get("/admin/societe/1")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonSocieteDTO.write(societe).json, response.contentAsString)
    }

    @Test
    fun uneCreationDeSociete(){
        val societe = SocieteToCreate(
            email = "fournisseur2@email.fr",
            nom = "Fournisseur2",
            role = Role.FOURNISSEUR
        )

        val response = mvc.perform(
            post("/admin/societe/create").contentType(MediaType.APPLICATION_JSON).content(
                jsonSocieteToCreate.write(societe).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(), response.status)
    }

    @Test
    fun uneSuppressionDeSociete(){
        val response = mvc.perform(
            delete("/admin/societe/delete/1")
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }

    /*@Test
    fun uneMiseAJourDuneSociete(){
        val response = mvc.perform(
            put("/admin/societe/update").contentType(MediaType.APPLICATION_JSON).content(
                jsonSociete.write(Societe()).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
    }*/

}