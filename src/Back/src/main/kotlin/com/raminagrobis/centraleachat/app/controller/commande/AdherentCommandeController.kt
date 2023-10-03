package com.raminagrobis.centraleachat.app.controller.commande

import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Achat adherent")
@RestController
@RequestMapping("/adherent/achats")
class AdherentCommandeController(
    val effectuerAchat: EffectuerAchat,
    val annulerAchat: AnnulerAchat
){
    @PostMapping()
    fun addAchat(@RequestBody achat: AchatMin) : ResponseEntity<String> {
        effectuerAchat.handle(achat)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping()
    fun deleteAchat(@RequestBody key: AchatKey) : ResponseEntity<String> {
        annulerAchat.handle(key)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}