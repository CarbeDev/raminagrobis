package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommandeController(
    val effectuerAchat: EffectuerAchat,
    val annulerAchat: AnnulerAchat
) {
    @PostMapping(name = "/achat/add")
    fun addAchat(@RequestBody achat: Achat){
        effectuerAchat.handle(achat)
    }

    @DeleteMapping(name = "/achat/delete")
    fun deleteAchat(@RequestBody achat: Achat){
        annulerAchat.handle(achat)
    }
}