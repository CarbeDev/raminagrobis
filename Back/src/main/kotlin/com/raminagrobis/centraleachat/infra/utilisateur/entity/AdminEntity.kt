package com.raminagrobis.centraleachat.infra.utilisateur.entity

import com.raminagrobis.centraleachat.domain.administration.model.Role
import jakarta.persistence.*

@Entity(name = "admin")
class AdminEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    var id : Int = 0,

    @Column(
        name = "email_admin",
        nullable = false
    )
    var email : String = "",

    @Column(name = "mdp_admin")
    var motDePasse : String = "",

    @Transient
    val role: Role = Role.ADMIN
)