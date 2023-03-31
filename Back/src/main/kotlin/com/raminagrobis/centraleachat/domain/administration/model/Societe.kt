package com.raminagrobis.centraleachat.domain.administration.model

import com.raminagrobis.centraleachat.domain.commande.model.Achat
import jakarta.persistence.*

@Entity
class Societe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_societe")
    var id: Int? = null,
    @Column(name = "nom_societe", nullable = false)
    var nom : String? = null,
    @Column(nullable = false)
    var email: String? = null,
    var motDePasse :String? = null,
    @Enumerated(EnumType.STRING)
    var role: Role? = null,
    @Column(nullable = false)
    var actif : Boolean? = null,

    @OneToMany(mappedBy = "societe")
    var historique : ArrayList<Achat> = arrayListOf()
)

enum class Role{
    ADHERENT,
    FOURNISSEUR
}
