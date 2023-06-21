package com.raminagrobis.centraleachat.app.controller.demande

import com.raminagrobis.centraleachat.domain.demande.model.Demande
import com.raminagrobis.centraleachat.domain.demande.usecase.FaireDemande
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Demande")
@RestController
class DemandeController(
    val faireDemande: FaireDemande
){

    @PostMapping("/demande")
    fun faireUneDemande(@RequestBody demande: Demande) : ResponseEntity<String>{
        faireDemande.handle(demande)
        return ResponseEntity(HttpStatus.OK)
    }
}