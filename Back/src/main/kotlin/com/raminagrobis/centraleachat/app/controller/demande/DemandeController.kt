package com.raminagrobis.centraleachat.app.controller.demande

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.usecase.AccepterDemande
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
    val faireDemande: FaireDemande,
    val accepterDemande: AccepterDemande
){

    @PostMapping("/demande")
    fun faireUneDemande(@RequestBody demande: DemandeDTO) : ResponseEntity<String>{
        faireDemande.handle(demande)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("admin/demande/accepter")
    fun accepterUneDemande(@RequestBody demande : DemandeGere) : ResponseEntity<String>{
        accepterDemande.handle(demande)
        return ResponseEntity(HttpStatus.CREATED)
    }
}