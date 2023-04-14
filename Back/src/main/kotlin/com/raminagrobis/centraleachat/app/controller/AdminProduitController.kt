package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Produit")
@RestController
class AdminProduitController(
    val recupererProduits: RecupererProduits,
    val recupererProduit: RecupererProduit,
    val ajouterProduit: AjouterProduit,
    val activerProduit: ActiverProduit,
    val desactiverProduit: DesactiverProduit
){

    @GetMapping("/admin/produits")
    fun getAllProduit(): Iterable<Produit> {
        return recupererProduits.handle()
    }

    @GetMapping("/admin/produit/{ref}")
    fun getProduit(@PathVariable ref:String) : Produit{
        return recupererProduit.handle(ref)
    }

    @PostMapping("/admin/produit/add")
    fun ajoutProduit(@RequestBody produit: Produit){
        ajouterProduit.handle(produit)
    }

    @PutMapping("/admin/produit/activate/{id}")
    fun activeProduit(ref :String){
        activerProduit.handle(ref)
    }

    @PutMapping("/admin/produit/desactivate/{ref}")
    fun desactiveProduit(@PathVariable ref : String){
        desactiverProduit.handle(ref)
    }
}