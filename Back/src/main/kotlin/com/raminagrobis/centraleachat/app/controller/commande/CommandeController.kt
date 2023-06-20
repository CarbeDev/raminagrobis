package com.raminagrobis.centraleachat.app.controller.commande

import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.commande.usecase.RecupererAchats
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Achat")
@RestController
class CommandeController(
    val effectuerAchat: EffectuerAchat,
    val annulerAchat: AnnulerAchat,
    val recupererAchats: RecupererAchats
) {

    @GetMapping("/achat/get")
    fun getAchatByFilter(@RequestParam referenceProduit : String, @RequestParam idPanier : String) : Iterable<AchatDTO>?{
        return recupererAchats.handle(referenceProduit, idPanier)
    }

    @PostMapping("/achat/add")
    fun addAchat(@RequestBody achat: AchatDTO){
        effectuerAchat.handle(achat)
    }

    @DeleteMapping("/achat/delete")
    fun deleteAchat(@RequestBody achat: AchatDTO){
        annulerAchat.handle(achat)
    }
}