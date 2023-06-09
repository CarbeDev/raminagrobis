package com.raminagrobis.centraleachat.app.controller.admin

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Produit")
@RestController
class AdminProduitController(
    val ajouterProduit: AjouterProduit,
    val activerProduit: ActiverProduit,
    val desactiverProduit: DesactiverProduit
){

    @PostMapping("/admin/produit/add")
    fun ajoutProduit(@RequestBody produit: ProduitDTO){
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