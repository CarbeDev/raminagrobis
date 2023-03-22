package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.administration.usecase.CreationSociete
import com.raminagrobis.centraleachat.domain.administration.usecase.GetSocietes
import com.raminagrobis.centraleachat.domain.administration.usecase.MiseAJourSociete
import com.raminagrobis.centraleachat.domain.administration.usecase.SuppressionSociete
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(val getSocietes: GetSocietes, val creationSociete: CreationSociete, val suppressionSociete: SuppressionSociete, val miseAJourSociete: MiseAJourSociete){

    @GetMapping("admin/societes")
    fun getSocietes():Iterable<Societe>{
         return getSocietes.handle()
    }

    @PostMapping("admin/societe/create")
    fun createSociete(@RequestBody societeToCreate: SocieteToCreate){
        creationSociete.handle(societeToCreate.email,societeToCreate.nom,societeToCreate.role)
    }

    @DeleteMapping("admin/societe/delete/{id}")
    fun deleteSociete(@PathVariable id : Int){
        suppressionSociete.handle(id)
    }

    @PutMapping("admin/societe/update")
    fun updateSociete(@RequestBody societe: Societe){
        miseAJourSociete.handle(societe)
    }
}

data class SocieteToCreate(val email : String, val nom : String,val role: Role)