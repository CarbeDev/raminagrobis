package com.raminagrobis.centraleachat.domain.demande.model

import com.raminagrobis.centraleachat.domain.administration.model.Categorie
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import jakarta.persistence.*

@Entity
class Demande(
    @Id
    @Column(name = "id_demande")
    var id : Int = 0,
    @ManyToOne
    @JoinColumn(name = "id_societe")
    var societe : Societe? = null,
    var nom : String = "",
    var description : String = "",
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    var categorie: Categorie? = null,
    @Column(name = "etat_demande")
    var etat : EtatDemande? = EtatDemande.EN_COURS
)

enum class EtatDemande{
    EN_COURS,
    ACCEPTE,
    REFUSER
}