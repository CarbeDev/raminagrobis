package com.raminagrobis.centraleachat.app.controller.produit

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.usecase.RecupererProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.RecupererProduits
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Produit")
@RestController
class ProduitController(
    val recupererProduits: RecupererProduits,
    val recupererProduit: RecupererProduit
){

    @GetMapping("/produits")
    fun getAllProduit(): Iterable<ProduitDetail> {
        return recupererProduits.handle()
    }

    @GetMapping("/produit/{ref}")
    fun getProduit(@PathVariable ref:String) : ProduitDetail {
        return recupererProduit.handle(ref)
    }
}