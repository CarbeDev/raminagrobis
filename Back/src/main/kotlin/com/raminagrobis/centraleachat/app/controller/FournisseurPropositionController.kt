package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.EnleverUnePropositionDePrix
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Proposition")
@RestController
class FournisseurPropositionController(
    val faireUnePropositionDePrix: FaireUnePropositionDePrix,
    val enleverUnePropositionDePrix: EnleverUnePropositionDePrix
) {

    @PostMapping("/fournisseur/proposition/add")
    fun addProposition(@RequestBody proposition: Proposition){
        faireUnePropositionDePrix.handle(proposition)
    }

    @DeleteMapping("/fournisseur/proposition/delete")
    fun deleteProposition(@RequestBody proposition: Proposition){
        enleverUnePropositionDePrix.handle(proposition)
    }
}