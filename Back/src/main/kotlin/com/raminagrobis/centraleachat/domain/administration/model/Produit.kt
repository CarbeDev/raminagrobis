package com.raminagrobis.centraleachat.domain.administration.model

import jakarta.persistence.*

@Entity
class Produit(
    @Id
    var reference : String? = null,
    @Column(name = "nom_produit", nullable = false)
    var nom : String? = null,
    @Column(name = "description_produit")
    var description: String? = null,
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    var categorie: Categorie? = null
)
