package com.raminagrobis.centraleachat.app.controller

import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.ActiverProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.AjouterUnProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.DesactiverProduit
import com.raminagrobis.centraleachat.domain.administration.usecase.GetProduits
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class AdminProduitController(
    val getProduits: GetProduits,
    val ajouterUnProduit: AjouterUnProduit,
    val activerProduit: ActiverProduit,
    val desactiverProduit: DesactiverProduit
){

    @GetMapping("/admin/produits")
    fun getAllProduit(): Iterable<Produit> {
        return getProduits.handle()
    }

    @PostMapping("/admin/produit/add")
    fun ajoutProduit(@RequestBody produit: Produit){
        ajouterUnProduit.handle(produit)
    }

    @PutMapping("/admin/produit/activate/{id}")
    fun activeProduit(id :String){
        activerProduit.handle(id)
    }

    @PutMapping("/admin/produit/desactivate/{ref}")
    fun desactiveProduit(@PathVariable ref : String){
        desactiverProduit.handle(ref)
    }
}