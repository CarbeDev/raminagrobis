package com.raminagrobis.centraleachat.app.controller.commande

import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.usecase.RecupererAchats
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Achat")
@RestController
@RequestMapping("/achats")
class CommandeController(
    val recupererAchats: RecupererAchats
) {

    @GetMapping()
    fun getAchatByFilter(@RequestParam referenceProduit : String?, @RequestParam idPanier : String?) : Iterable<AchatDetail>{
        return recupererAchats.handle(referenceProduit, idPanier)
    }
}