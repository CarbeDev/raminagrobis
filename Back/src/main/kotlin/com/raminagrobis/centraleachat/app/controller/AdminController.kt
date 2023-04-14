package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import io.swagger.v3.oas.annotations.tags.Tag
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
    fun getSocietes():Iterable<Societe>{
         return recupererSocietes.handle()
    }

    @GetMapping("admin/societe/{id}")
    fun getSociete(@PathVariable id :Int):Societe{
        return recupererSociete.handle(id)
    }

    @PostMapping("admin/societe/create")
    fun createSociete(@RequestBody societeToCreate: SocieteToCreate){
        creerSociete.handle(societeToCreate.email,societeToCreate.nom,societeToCreate.role)
    }

    @DeleteMapping("admin/societe/delete/{id}")
    fun deleteSociete(@PathVariable id : Int){
        supprimerSociete.handle(id)
    }

    @PutMapping("admin/societe/update")
    fun updateSociete(@RequestBody societe: Societe){
        miseAJourSociete.handle(societe)
    }
}

data class SocieteToCreate(val email : String, val nom : String,val role: Role)