package com.raminagrobis.centraleachat.domain.administration.model

import jakarta.persistence.*

@Entity
class Produit(
    @Id
    var reference : String = "",
    @Column(name = "nom_produit", nullable = false)
    var nom : String = "",
    @Column(name = "description_produit")
    var description: String = "",
    var actif : Boolean = false,
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    var categorie: Categorie? = null
)
