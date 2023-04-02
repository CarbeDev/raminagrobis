package com.raminagrobis.centraleachat.domain.commande.model

import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import jakarta.persistence.*
import java.io.Serializable

@Entity
class Achat(
    @EmbeddedId
    var key : AchatKey? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_societe", referencedColumnName = "id_societe", insertable = false, updatable = false)
    var societe : Societe? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_produit", referencedColumnName = "reference", insertable = false, updatable = false)
    var produit: Produit? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_panier", referencedColumnName = "id_panier", insertable = false, updatable = false)
    var panier: Panier? = null,


    var quantite: Int= 0
)

@Embeddable
class AchatKey(

    @Column(name = "id_societe")
    var idSociete: Int = 0,

    @Column(name = "reference_produit")
    var reference : String? = null,

    @Column(name = "id_panier")
    var idpanier : String = ""

) : Serializable