package com.raminagrobis.centraleachat.controller.commande

import com.fasterxml.jackson.databind.ObjectMapper
import com.raminagrobis.centraleachat.app.controller.commande.CommandeController
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.RecupererAchats
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class CommandeControllerTest {

    private lateinit var mvc : MockMvc

    @Mock
    private lateinit var effectuerAchat: EffectuerAchat
    @Mock
    private lateinit var annulerAchat: AnnulerAchat
    @Mock
    private lateinit var recupererAchats: RecupererAchats
    @InjectMocks
    private lateinit var controller : CommandeController

    private lateinit var jsonAchat : JacksonTester<Achat>

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        JacksonTester.initFields(this, ObjectMapper())
    }

    //@Test
    //fun
}