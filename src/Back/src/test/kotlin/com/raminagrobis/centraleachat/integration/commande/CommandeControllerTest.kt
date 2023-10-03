package com.raminagrobis.centraleachat.integration.commande

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.app.controller.commande.CommandeController
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.usecase.RecupererAchats
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
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class CommandeControllerTest {

    private lateinit var mvc : MockMvc
    @Mock
    private lateinit var recupererAchats: RecupererAchats
    @InjectMocks
    private lateinit var controller : CommandeController

    private lateinit var jsonAchatDetail : JacksonTester<Iterable<AchatDetail>>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun doitRetournerDesAchats(){

        val refProduit = "B550MDS3H"
        val idPanier = "23-23"

        val resultat = listOf(
            AchatDetail(
                adherent = SocieteDTO(
                    id = 1,
                    nom = "Free agent",
                    email = "Freeagent@Adherent.com",
                    role = Role.ADHERENT,
                    actif = true
                ),
                produit = ProduitDetail(
                    reference = "B550MDS3H",
                    nom = "Gigabyte B550M DS3H",
                    description = "Une carte mere",
                    categorie = CategorieDTO(
                        id=1,
                        libelle = "Carte mere"),
                    actif = true,
                ),
                panier = PanierDTO(
                    id = "23-23",
                    etatPanier = EtatPanier.OUVERT,
                    listeAchat = listOf()
                ),
                quantite = 50
            ),
            AchatDetail(adherent = SocieteDTO(
                id = 2,
                nom = "Adherent1",
                email = "Adherent1@email.fr",
                role = Role.ADHERENT,
                actif = true
            ),
                produit = ProduitDetail(
                    reference = "B550MDS3H",
                    nom = "Gigabyte B550M DS3H",
                    description = "Une carte mere",
                    categorie = CategorieDTO(
                        id=1,
                        libelle = "Carte mere"),
                    actif = true,
                ),
                panier = PanierDTO(
                    id = "23-23",
                    etatPanier = EtatPanier.OUVERT,
                    listeAchat = listOf()
                ),
                quantite = 50)
        )

        `when`(recupererAchats.handle(refProduit, idPanier)).thenReturn(resultat)

        val response = mvc.perform(
            get("/achats?referenceProduit=B550MDS3H&idPanier=23-23")
        ).andReturn().response

        verify(recupererAchats, times(1)).handle("B550MDS3H", "23-23")
        assertEquals(jsonAchatDetail.write(resultat).json, response.contentAsString)
        assertEquals(HttpStatus.OK.value(), response.status)

    }

}