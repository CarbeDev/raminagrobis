package com.raminagrobis.centraleachat.controller.demande

import com.fasterxml.jackson.databind.ObjectMapper
import com.raminagrobis.centraleachat.app.controller.demande.DemandeController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.usecase.AccepterDemande
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
    @Mock
    private lateinit var accepterDemande: AccepterDemande

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
            categorie = CategorieDTO(
                id = 1,
                libelle = "Souris"
            ),
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            )
        )

        val response = mvc.perform(
            post("/demande").contentType(MediaType.APPLICATION_JSON).content(
                jsonDemande.write(demande).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(),response.status)
    }

    @Test
    fun uneDemandeEstAccepte(){

        val demandeGere = DemandeGere(
            produit = ProduitDTO(
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
            post("/admin/demande/accepter").contentType(MediaType.APPLICATION_JSON).content(
                jsonDemandeGere.write(demandeGere).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(), response.status)
    }
}