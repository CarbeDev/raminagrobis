package com.raminagrobis.centraleachat.app.controller.admin

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.usecase.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Produit")
@RestController
@RequestMapping("/admin/produits/")
class AdminProduitController(
    val ajouterProduit: AjouterProduit,
    val activerProduit: ActiverProduit,
    val desactiverProduit: DesactiverProduit
){

    @PostMapping()
    fun ajoutProduit(@RequestBody produit: ProduitDTO) : ResponseEntity<String>{
        ajouterProduit.handle(produit)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("activate/{ref}")
    fun activeProduit(@PathVariable ref :String) : ResponseEntity<String>{
        activerProduit.handle(ref)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("desactivate/{ref}")
    fun desactiveProduit(@PathVariable ref : String) : ResponseEntity<String>{
        desactiverProduit.handle(ref)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}