package com.raminagrobis.centraleachat.domain.administration.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Categorie(
    @Id
    @Column(name = "id_categorie")
    var id : Int = 0,
    @Column(name = "libelle_categorie")
    var libelle : String = "",
    @OneToMany(mappedBy = "categorie")
    var  produits : Set<Produit> = emptySet()
)