package com.raminagrobis.centraleachat.integration.commande

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.app.controller.commande.AdherentCommandeController
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import org.junit.jupiter.api.Assertions
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class AdherentCommandeControllerTest {

    private lateinit var mvc : MockMvc
    @Mock
    private lateinit var effectuerAchat : EffectuerAchat
    @Mock
    private lateinit var annulerAchat : AnnulerAchat
    @InjectMocks
    private lateinit var controller : AdherentCommandeController

    private lateinit var jsonAchatDTO : JacksonTester<AchatMin>
    private lateinit var jsonKey : JacksonTester<AchatKey>


    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper())
    }


    @Test
    fun doitAjouterUnAchat(){
        val achat = AchatMin(
            idAdherent = 1,
            idPanier = "25-23",
            refProduit = "VisPro",
            quantite = 125
        )

        val response = mvc.perform(
            MockMvcRequestBuilders.post("/adherent/achats").contentType(MediaType.APPLICATION_JSON).content(
                jsonAchatDTO.write(achat).json
            )
        ).andReturn().response

        verify(effectuerAchat, times(1)).handle(achat)
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.status)
    }

    @Test
    fun doitSupprimerUnAchat(){
        val key = AchatKey(
            idSociete = 1,
            idPanier = "25-23",
            reference = "VisPro"
        )

        val response = mvc.perform(
            MockMvcRequestBuilders.delete("/adherent/achats").contentType(MediaType.APPLICATION_JSON).content(
                jsonKey.write(key).json
            )
        ).andReturn().response

        verify(annulerAchat, times(1)).handle(any())
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.status)
    }
}