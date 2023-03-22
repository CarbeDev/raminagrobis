package com.raminagrobis.centraleachat.domain.administration.model

import jakarta.persistence.*

@Entity
class Societe(
    @Column(name = "nom_societe")
    var nom : String? = null,
    @Column(nullable = false)
    var email: String? = null,
    @Column(nullable = false)
    var actif : Boolean? = null,
    @Enumerated(EnumType.STRING)
    var role: Role? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_societe")
    var id: Int? = null
)

enum class Role{
    ADHERENT,
    FOURNISSEUR
}
