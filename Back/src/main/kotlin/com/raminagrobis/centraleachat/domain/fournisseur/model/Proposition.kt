package com.raminagrobis.centraleachat.domain.fournisseur.model

import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.model.Societe
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
    var produit : Produit? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("idSociete")
    @JoinColumn(name = "id_societe", referencedColumnName = "id_societe", insertable = false, updatable = false)
    var societe: Societe? = null,

    var prix : BigDecimal? = null
)

@Embeddable
class PropositionKey(
    @Column(name = "reference_produit")
    var reference : String? = null,
    @Column(name = "id_societe")
    var idSociete: Int? = null

) : Serializable {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
