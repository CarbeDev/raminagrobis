package com.raminagrobis.centraleachat.domain.commande.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Panier(
    @Id
    @Column(name = "id_panier")
    var id : String = "",

    @OneToMany(mappedBy = "panier")
    var listeAchat : List<Achat> = listOf()
)