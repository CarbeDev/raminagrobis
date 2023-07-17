package com.raminagrobis.centraleachat.app.controller.admin

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteToCreate
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import com.raminagrobis.centraleachat.domain.administration.usecase.RecupererSocietes.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Societe")
@RestController
@RequestMapping("admin/societes/")
class AdminSocieteController(
    val recupererSocietes: RecupererSocietes,
    val recupererSociete: RecupererSociete,
    val creerSociete: CreerSociete,
    val supprimerSociete: SupprimerSociete,
    val miseAJourSociete: MiseAJourSociete
){

    @GetMapping("")
    fun getSocietes():Iterable<SocieteDTO>{
         return recupererSocietes.handle()
    }

    @GetMapping("{id}")
    fun getSociete(@PathVariable id :Int):DetailSociete{
        return recupererSociete.handle(id)
    }

    @PostMapping("")
    fun createSociete(@RequestBody societeToCreate: SocieteToCreate) : ResponseEntity<String>{
        creerSociete.handle(societeToCreate.email,societeToCreate.nom,societeToCreate.role)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun deleteSociete(@PathVariable id : Int) : ResponseEntity<String>{
        supprimerSociete.handle(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("")
    fun updateSociete(@RequestBody societe: SocieteDTO) : ResponseEntity<String>{
        miseAJourSociete.handle(societe)
        return ResponseEntity(HttpStatus.OK)
    }
}

