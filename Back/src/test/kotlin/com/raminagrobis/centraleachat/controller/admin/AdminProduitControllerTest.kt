package com.raminagrobis.centraleachat.controller.admin

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.app.controller.admin.AdminProduitController
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.usecase.ActiverProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.AjouterProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.DesactiverProduit
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class AdminProduitControllerTest {

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var ajouterProduit: AjouterProduit

    @Mock
    private lateinit var activerProduit: ActiverProduit

    @Mock
    private lateinit var desactiverProduit: DesactiverProduit

    @InjectMocks
    private lateinit var controller : AdminProduitController

    private lateinit var jsonProduitDTO : JacksonTester<ProduitDTO>

    @BeforeEach
    fun setup(){
        JacksonTester.initFields(this, ObjectMapper())
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun doitAjouterLeProduit(){

        val produit = ProduitDTO(
            reference = "VisPRO",
            nom = "Apple Vision Pro",
            description = "Revolutionnaire",
            actif = true,
            idCategorie = 4
        )

        val response = mvc.perform(
            post("/admin/produits/").contentType(MediaType.APPLICATION_JSON).content(
                jsonProduitDTO.write(produit).json
            )
        ).andReturn().response

        verify(ajouterProduit, times(1)).handle(produit)
        assertEquals(HttpStatus.CREATED.value(), response.status)
    }

    @Test
    fun doitActiverLeProduit(){
        val response = mvc.perform(
            put("/admin/produits/activate/VisPRO")
        ).andReturn().response

        verify(activerProduit, times(1)).handle("VisPRO")
        assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }

    @Test
    fun doitDesactiverLeProduit(){
        val response = mvc.perform(
            put("/admin/produits/desactivate/VisPRO")
        ).andReturn().response

        verify(desactiverProduit, times(1)).handle("VisPRO")
        assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }

}