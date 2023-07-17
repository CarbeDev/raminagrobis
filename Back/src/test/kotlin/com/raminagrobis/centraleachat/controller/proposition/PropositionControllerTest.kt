package com.raminagrobis.centraleachat.controller.proposition

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.app.controller.proposition.PropositionController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
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

    private lateinit var jsonPropositions : JacksonTester<Iterable<PropositionDTO>>

    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun DesPropositionsPeuventEtreRecupererAPartirDeLeurFournisseur(){

        val resultat = listOf(
            PropositionDTO(
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
            ),
            PropositionDTO(
                societe = SocieteDTO(
                    id = 1,
                    nom = "Fournisseur1",
                    email = "fournisseur1@email.fr",
                    role = Role.FOURNISSEUR,
                    actif = false
                ),
                produit = ProduitDetail(
                    reference = "LSD",
                    nom = "Logitech StreamDeck",
                    description = "Tres utile",
                    categorie = CategorieDTO(
                        id = 3,
                        libelle = "Autre"
                    ),
                    actif = false
                ),
                prix = BigDecimal(72.99)
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
            PropositionDTO(
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
            ),
            PropositionDTO(
                societe = SocieteDTO(
                    id = 1,
                    nom = "Fournisseur2",
                    email = "fournisseur2@email.fr",
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
                prix = BigDecimal(3450)
            ),
        )

        `when`(recupererPropositions.handle("LG502")).doReturn(resultat)

        val response = mvc.perform(
            get("/propositions/produit/LG502").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonPropositions.write(resultat).json, response.contentAsString)
    }
}