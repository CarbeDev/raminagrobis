package com.raminagrobis.centraleachat.app.controller.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireUnePropositionDePrix
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.SupprimerUnePropositionDePrix
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Proposition")
@RestController
@RequestMapping("/fournisseur/proposition")
class FournisseurPropositionController(
    val faireUnePropositionDePrix: FaireUnePropositionDePrix,
    val supprimerUnePropositionDePrix: SupprimerUnePropositionDePrix,
) {

    @PostMapping()
    fun addProposition(@RequestBody proposition: PropositionDTO) : ResponseEntity<String>{
        faireUnePropositionDePrix.handle(proposition)
        return ResponseEntity<String>(HttpStatus.CREATED)
    }

    @DeleteMapping()
    fun deleteProposition(@RequestBody propositionKey: PropositionKey ) : ResponseEntity<String>{
        supprimerUnePropositionDePrix.handle(propositionKey)
        return  ResponseEntity<String>(HttpStatus.NO_CONTENT)
    }
}