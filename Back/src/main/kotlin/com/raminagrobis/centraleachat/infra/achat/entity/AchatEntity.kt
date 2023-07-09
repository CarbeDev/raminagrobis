package com.raminagrobis.centraleachat.infra.achat.entity

import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal

@Entity
class AchatEntity(
    @EmbeddedId
    var key : AchatKey? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_societe", referencedColumnName = "id_societe", insertable = false, updatable = false)
    var adherent : SocieteEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_produit", referencedColumnName = "reference", insertable = false, updatable = false)
    var produit: ProduitEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_panier", referencedColumnName = "id_panier", insertable = false, updatable = false)
    var panier: PanierEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_societe", referencedColumnName = "id_societe", insertable = false, updatable = false)
    var fournisseur : SocieteEntity? = null,

    var quantite: Int= 0,

    val prixUnitaire : BigDecimal? = null
)

@Embeddable
class AchatKey(

    @Column(name = "id_societe")
    var idSociete: Int = 0,

    @Column(name = "reference_produit")
    var reference : String = "",

    @Column(name = "id_panier")
    var idpanier : String = ""

) : Serializable{
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
    override fun hashCode(): Int {
        return super.hashCode()
    }
}