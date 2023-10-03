package com.raminagrobis.centraleachat.infra.produit.entity

import jakarta.persistence.*

@Entity
class ProduitEntity(
    @Id
    var reference : String = "",
    @Column(name = "nom_produit", nullable = false)
    var nom : String = "",
    @Column(name = "description_produit")
    var description: String = "",
    var actif : Boolean = false,
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    var categorie: CategorieEntity? = null
)
