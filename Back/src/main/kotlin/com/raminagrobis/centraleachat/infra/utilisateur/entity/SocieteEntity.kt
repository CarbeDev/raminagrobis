package com.raminagrobis.centraleachat.infra.utilisateur.entity

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import jakarta.persistence.*
import java.sql.Date

@Entity(name = "Societe")
class SocieteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_societe")
    var id: Int = 0,
    @Column(name = "nom_societe", nullable = false)
    var nom : String = "",
    @Column(
        name = "email_societe",
        unique = true,
        nullable = false
    )
    var email: String = "",
    @Column(name = "mdp_societe")
    var motDePasse :String = "",
    @Enumerated(EnumType.STRING)
    var role: Role? = null,
    var dateInscription : Date? = null,
    @Column(nullable = false)
    var actif : Boolean = false,
    @OneToMany(mappedBy = "adherent")
    var historique : List<AchatEntity> = listOf()
)