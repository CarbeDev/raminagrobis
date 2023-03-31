package com.raminagrobis.centraleachat.domain.commande.model

import jakarta.persistence.*

@Entity
class Panier(
    @Id
    @Column(name = "id_panier")
    var id : String = "",

    @OneToMany(mappedBy = "panier")
    var listeAchat : List<Achat> = listOf(),

    @Enumerated(EnumType.STRING)
    var etat: Etat = Etat.FERMER
)

enum class Etat {
    OUVERT,
    FERMER
}

