package com.raminagrobis.centraleachat.app.controller.demande

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.usecase.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Demande")
@RestController
class DemandeController(
    val recupererDemande: RecupererDemande,
    val recupererDemandes: RecupererDemandes,
    val faireDemande: FaireDemande,
    val accepterDemande: AccepterDemande,
    val refuserDemande: RefuserDemande
){

    @GetMapping("/demandes/{id}")
    fun recupererUneDemande(@PathVariable id: Int) : DemandeDTO{
        return recupererDemande.handle(id)
    }

    @GetMapping("/demandes")
    fun recupererDesDemandes() : Iterable<DemandeDTO>{
        return recupererDemandes.handle()
    }
    @PostMapping("/demandes")
    fun faireUneDemande(@RequestBody demande: DemandeDTO) : ResponseEntity<String>{
        faireDemande.handle(demande)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("admin/demandes")
    fun accepterUneDemande(@RequestBody demande : DemandeGere) : ResponseEntity<String>{
        accepterDemande.handle(demande)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("admin/demandes/{id}")
    fun refuserUneDemande(@PathVariable id : Int) : ResponseEntity<String>{
        refuserDemande.handle(id)
        return ResponseEntity(HttpStatus.OK)
    }
}