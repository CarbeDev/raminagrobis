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
    @Column(name = "etat_panier")
    var etatPanier: EtatPanier = EtatPanier.FERMER
)

enum class EtatPanier {
    OUVERT,
    FERMER
}

