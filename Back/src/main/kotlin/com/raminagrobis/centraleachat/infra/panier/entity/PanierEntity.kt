package com.raminagrobis.centraleachat.infra.panier.entity

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import jakarta.persistence.*

@Entity
class PanierEntity(
    @Id
    @Column(name = "id_panier")
    var id : String = "",

    @OneToMany(mappedBy = "panier")
    var listeAchat : List<Achat> = listOf(),

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_panier")
    var etatPanier: EtatPanier = EtatPanier.FERMER
)