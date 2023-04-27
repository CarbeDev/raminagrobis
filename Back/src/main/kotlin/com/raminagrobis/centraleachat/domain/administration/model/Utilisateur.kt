package com.raminagrobis.centraleachat.domain.administration.model

import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import jakarta.persistence.*

@Entity
class Societe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_societe")
    var id: Int? = null,
    @Column(name = "nom_societe", nullable = false)
    var nom : String? = null,
    @Column(
        name = "email_societe",
        nullable = false
    )
    override var email: String? = null,
    @Column(name = "mdp_societe")
    override var motDePasse :String? = null,
    @Enumerated(EnumType.STRING)
    override var role: Role? = null,
    @Column(nullable = false)
    var actif : Boolean? = null,

//    @OneToMany(mappedBy = "societe")
//    var historique : ArrayList<Achat> = arrayListOf()
) : Utilisateur


@Entity
class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    var id : Int = 0,

    @Column(
        name = "email_admin",
        nullable = false
    )
    override var email : String = "",

    @Column(name = "mdp_admin")
    override var motDePasse : String = "",

    @Transient
    override var role: Role? = Role.ADMIN
) : Utilisateur


enum class Role{
    ADHERENT,
    FOURNISSEUR,
    ADMIN
}
