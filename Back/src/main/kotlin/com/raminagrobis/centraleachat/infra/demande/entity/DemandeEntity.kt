package com.raminagrobis.centraleachat.infra.demande.entity

import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import jakarta.persistence.*

@Entity
class DemandeEntity(
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
