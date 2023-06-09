package com.raminagrobis.centraleachat.domain.administration.dto

data class ProduitDTO(
    var reference: String,
    var nom : String,
    var description : String,
    var actif : Boolean,
    var categorie : CategorieDTO
)