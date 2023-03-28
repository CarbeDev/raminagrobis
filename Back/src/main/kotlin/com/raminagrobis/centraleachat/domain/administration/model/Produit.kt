package com.raminagrobis.centraleachat.domain.administration.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Produit(
    @Id
    var reference : String? = null,
    @Column(name = "nom_produit", nullable = false)
    var nom : String? = null
)
