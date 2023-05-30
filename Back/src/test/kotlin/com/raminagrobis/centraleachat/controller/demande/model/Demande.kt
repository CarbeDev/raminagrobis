package com.raminagrobis.centraleachat.controller.demande.model

import com.raminagrobis.centraleachat.domain.administration.model.Categorie
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import jakarta.persistence.Column

class Demande(
    @Column(name = "id_demande")
    var id : Int = 0,
    @Column(name = "id_societe")
    var societe : Societe? = null,
    @Column(name = "nom_produit_demande")
    var nom : String = "",
    @Column(name = "description_produit_demande")
    var description : String = "",
    @Column(name = "id_categorie_produit_demande")
    var categorie: Categorie? = null,
    @Column(name = "etat_demande")
    var etat : EtatDemande? = null
)

enum class EtatDemande{
    EN_COURS,
    ACCEPTE,
    REFUSER
}