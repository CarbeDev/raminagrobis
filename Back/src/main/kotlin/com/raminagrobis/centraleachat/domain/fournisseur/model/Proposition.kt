package com.raminagrobis.centraleachat.domain.fournisseur.model

import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal

@Entity
class Proposition(
    @EmbeddedId
    var propositionKey: PropositionKey? = null,

    @ManyToOne(fetch =  FetchType.LAZY)
    //@MapsId("referenceProduit")
    @JoinColumn(name = "reference_produit", referencedColumnName = "reference", insertable = false, updatable = false)
    var produit : ProduitEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("idSociete")
    @JoinColumn(name = "id_societe", referencedColumnName = "id_societe", insertable = false, updatable = false)
    var societe: Societe? = null,

    var prix : BigDecimal = BigDecimal(0)
)

@Embeddable
class PropositionKey(
    @Column(name = "reference_produit")
    var reference : String = "",
    @Column(name = "id_societe")
    var idSociete: Int = 0

) : Serializable {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
