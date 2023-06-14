package com.raminagrobis.centraleachat.app.controller.admin

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import com.raminagrobis.centraleachat.domain.administration.usecase.RecupererSocietes.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Societe")
@RestController
class AdminController(
    val recupererSocietes: RecupererSocietes,
    val recupererSociete: RecupererSociete,
    val creerSociete: CreerSociete,
    val supprimerSociete: SupprimerSociete,
    val miseAJourSociete: MiseAJourSociete
){

    @GetMapping("admin/societes")
    fun getSocietes():Iterable<SocieteDTO>{
         return recupererSocietes.handle()
    }

    @GetMapping("admin/societe/{id}")
    fun getSociete(@PathVariable id :Int):DetailSociete{
        return recupererSociete.handle(id)
    }

    @PostMapping("admin/societe/create")
    fun createSociete(@RequestBody societeToCreate: SocieteToCreate) : ResponseEntity<String>{
        creerSociete.handle(societeToCreate.email,societeToCreate.nom,societeToCreate.role)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("admin/societe/delete/{id}")
    fun deleteSociete(@PathVariable id : Int) : ResponseEntity<String>{
        supprimerSociete.handle(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("admin/societe/update")
    fun updateSociete(@RequestBody societe: SocieteDTO) : ResponseEntity<String>{
        miseAJourSociete.handle(societe)
        return ResponseEntity(HttpStatus.OK)
    }
}

data class SocieteToCreate(val email : String, val nom : String,val role: Role)