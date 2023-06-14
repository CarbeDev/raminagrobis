package com.raminagrobis.centraleachat.domain.demande.model

import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import jakarta.persistence.*

@Entity
class Demande(
    @Id
    @Column(name = "id_demande")
    var id : Int = 0,
    @ManyToOne
    @JoinColumn(name = "id_societe")
    var societe : SocieteEntity? = null,
    var nom : String = "",
    var description : String = "",
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    var categorie: CategorieEntity? = null,
    @Column(name = "etat_demande")
    var etat : EtatDemande? = EtatDemande.EN_COURS
)

enum class EtatDemande{
    EN_COURS,
    ACCEPTE,
    REFUSER
}