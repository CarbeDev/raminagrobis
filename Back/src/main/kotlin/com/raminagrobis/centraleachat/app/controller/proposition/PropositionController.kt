package com.raminagrobis.centraleachat.app.controller.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.RecupererPropositions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PropositionController(
    val recupererPropositions: RecupererPropositions
){
    @GetMapping("/propositions/produit/{refProduit}")
    fun getProduitByProduit(@PathVariable refProduit : String) : Iterable<Proposition>{
        return recupererPropositions.handle(refProduit)
    }

    @GetMapping("/propositions/fournisseur/{idSociete}")
    fun getPropositionByFournisseur(@PathVariable idSociete : Int) : Iterable<Proposition>{
        return recupererPropositions.handle(idSociete)
    }
}