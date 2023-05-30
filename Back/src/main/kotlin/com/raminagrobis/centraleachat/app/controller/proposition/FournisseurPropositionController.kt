package com.raminagrobis.centraleachat.app.controller.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.SupprimerUnePropositionDePrix
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Proposition")
@RestController
class FournisseurPropositionController(
    val faireUnePropositionDePrix: FaireUnePropositionDePrix,
    val supprimerUnePropositionDePrix: SupprimerUnePropositionDePrix
) {

    @PostMapping("/fournisseur/proposition/add")
    fun addProposition(@RequestBody proposition: Proposition) : ResponseEntity<String>{
        faireUnePropositionDePrix.handle(proposition)
        return ResponseEntity<String>(HttpStatus.CREATED)
    }

    @DeleteMapping("/fournisseur/proposition/delete")
    fun deleteProposition(@RequestBody proposition: Proposition) : ResponseEntity<String>{
        supprimerUnePropositionDePrix.handle(proposition)
        return  ResponseEntity<String>(HttpStatus.NO_CONTENT)
    }
}