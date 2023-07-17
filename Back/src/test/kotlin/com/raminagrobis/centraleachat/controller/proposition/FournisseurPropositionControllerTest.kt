package com.raminagrobis.centraleachat.controller.proposition

import com.fasterxml.jackson.databind.ObjectMapper
import com.raminagrobis.centraleachat.app.controller.proposition.FournisseurPropositionController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
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
    private lateinit var proposition : PropositionDTO
    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        proposition = PropositionDTO(
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            ),
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
            prix = BigDecimal(3200)
        )
    }


    @Test
    fun unePropositionDePrixEstEnregistre(){

        val response = mvc.perform(
            post("/fournisseur/proposition/add").contentType(MediaType.APPLICATION_JSON).content(
                jsonProposition.write(proposition).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.CREATED.value(),response.status,)
    }

    @Test
    fun unePropositionDePrixEstSupprimer(){

        val response = mvc.perform(
            delete("/fournisseur/proposition/delete").contentType(MediaType.APPLICATION_JSON).content(
                jsonProposition.write(proposition).json
            )
        ).andReturn().response

        assertEquals(HttpStatus.NO_CONTENT.value(),response.status)
    }

}