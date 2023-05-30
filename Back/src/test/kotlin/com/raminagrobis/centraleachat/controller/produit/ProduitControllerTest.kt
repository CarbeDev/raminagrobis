package com.raminagrobis.centraleachat.controller.produit

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.raminagrobis.centraleachat.app.controller.produit.ProduitController
import com.raminagrobis.centraleachat.domain.administration.model.Categorie
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.*
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
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class ProduitControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var recupererProduits: RecupererProduits
    @Mock
    private lateinit var recupererProduit: RecupererProduit

    @InjectMocks
    private lateinit var controller : ProduitController

    private lateinit var jsonProduit : JacksonTester<Produit>
    private lateinit var jsonProduits : JacksonTester<List<Produit>>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this,ObjectMapper())
    }

    @Test
    fun desProduitsSontRecuperer(){
        val produits = listOf(
            Produit(
                reference = "LG203",
                nom = "Logitech G203",
                description = "Un tres bon produit",
                actif = true,
                categorie = Categorie(
                    id = 1,
                    libelle = "Souris"
                )
            ),
            Produit(
                reference = "HUAMV",
                nom = "Huawei MateView",
                description = "Un tres bon ecran",
                actif = true,
                categorie = Categorie(
                    id = 2,
                    libelle = "Ecran"
                )
            )
        )

        `when`(recupererProduits.handle()).doReturn(produits)

        val response = mvc.perform(
            get("/produits")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonProduits.write(produits).json, response.contentAsString)
    }

    @Test
    fun unProduitEstRecuperer(){
        val produit = Produit(
            reference = "LG203",
            nom = "Logitech G203",
            description = "Un tres bon produit",
            actif = true,
            categorie = Categorie(
                id = 1,
                libelle = "Souris"
            )
        )

        `when`(recupererProduit.handle("LG203")).doReturn(produit)

        val response = mvc.perform(
            get("/produit/LG203")
        ).andReturn().response

        assertEquals(HttpStatus.OK.value(), response.status)
        assertEquals(jsonProduit.write(produit).json, response.contentAsString)
    }
}