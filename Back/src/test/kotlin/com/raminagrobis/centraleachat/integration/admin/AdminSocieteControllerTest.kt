package com.raminagrobis.centraleachat.integration.admin

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.app.controller.admin.AdminSocieteController
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteToCreate
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import org.junit.jupiter.api.Assertions.assertEquals
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
import java.util.*

@ExtendWith(MockitoExtension::class)
class AdminSocieteControllerTest {

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
    private lateinit var controller : AdminSocieteController

    private lateinit var jsonSocieteDTOs: JacksonTester<Iterable<SocieteDTO>>
    private lateinit var jsonSocieteDTO: JacksonTester<SocieteDTO>
    private lateinit var jsonSocieteToCreate: JacksonTester<SocieteToCreate>
    private lateinit var jsonDetailSociete : JacksonTester<DetailSociete>
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
            get("/admin/societes/")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(),response.status)
        assertEquals(jsonSocieteDTOs.write(societes).json, response.contentAsString)
    }

    @Test
    fun uneRecuperationDeSociete(){
        val societe = DetailSociete(
            id = 1,
            nom = "Fournisseur1",
            email = "fournisseur1@email.fr",
            role = Role.FOURNISSEUR,
            actif = false,
            dateInscription = Date()
        )

        `when`(recupererSociete.handle(1)).doReturn(societe)

        val response = mvc.perform(
            get("/admin/societes/1")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonDetailSociete.write(societe).json, response.contentAsString)
    }

    @Test
    fun uneCreationDeSociete(){
        val societe = SocieteToCreate(
            email = "fournisseur2@email.fr",
            nom = "Fournisseur2",
            role = Role.FOURNISSEUR
        )

        val response = mvc.perform(
            post("/admin/societes/").contentType(MediaType.APPLICATION_JSON).content(
                jsonSocieteToCreate.write(societe).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(), response.status)
    }

    @Test
    fun uneCreationDeSocieteAvecUnEmailAuMauvaisFormatDoitRenvoyerUne422(){
        val societe = SocieteToCreate(
            email = "mauvaisEmail",
            nom = "Fournisseur2",
            role = Role.FOURNISSEUR
        )

        `when`(creerSociete.handle(societe.email,societe.nom,societe.role)).thenThrow(IllegalArgumentException::class.java)

        val response = mvc.perform(
            post("/admin/societes/").contentType(MediaType.APPLICATION_JSON).content(
                jsonSocieteToCreate.write(societe).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.status)
    }

    @Test
    fun uneCreationDeSocieteAvecUnEmailDejaUtilisertDoitRenvoyerUne409(){
        val societe = SocieteToCreate(
            email = "fournisseur2",
            nom = "Fournisseur2",
            role = Role.FOURNISSEUR
        )

        `when`(creerSociete.handle(societe.email,societe.nom,societe.role)).thenThrow(EmailAlreadyUseException::class.java)

        val response = mvc.perform(
            post("/admin/societes/").contentType(MediaType.APPLICATION_JSON).content(
                jsonSocieteToCreate.write(societe).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CONFLICT.value(), response.status)
    }

    @Test
    fun uneSuppressionDeSociete(){
        val response = mvc.perform(
            delete("/admin/societes/1")
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }

    @Test
    fun uneMiseAJourDuneSociete(){
        val response = mvc.perform(
            put("/admin/societes/").contentType(MediaType.APPLICATION_JSON).content(
                jsonSocieteDTO.write(SocieteDTO(
                    id = 1,
                    nom = "Fournisseur1",
                    email = "fournisseur1@email.fr",
                    role = Role.FOURNISSEUR,
                    actif = true
                )).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }

}