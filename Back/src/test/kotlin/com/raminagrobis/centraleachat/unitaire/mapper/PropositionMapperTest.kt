package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapperImpl
import com.raminagrobis.centraleachat.domain.administration.mapper.SocieteMapperImpl
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.domain.fournisseur.mapper.PropositionMapperImpl
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionEntity
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PropositionMapperTest {

    @Test
    fun entityToDTO(){
        val key = PropositionKey(
            idSociete = 1,
            reference = "VisPRO",
        )
        val entity = PropositionEntity(
            propositionKey = key,
            produit = ProduitEntity(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieEntity(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            societe = SocieteEntity(
                id = 1,
                nom = "raminagrobis",
                email = "raminagrobis@test.fr",
                motDePasse = "motDePasse",
                role = Role.FOURNISSEUR,
                actif = true,
                historique = listOf()
            ),
            prix = BigDecimal(3500)
        )

        val dto = PropositionMapperImpl().toDTO(entity)

        assertEquals(entity.propositionKey!!.idSociete, dto.societe.id)
        assertEquals(entity.propositionKey!!.reference, dto.produit.reference)
        assertEquals(SocieteMapperImpl().toDTO(entity.societe!!), dto.societe)
        assertEquals(ProduitMapperImpl().toDTO(entity.produit!!), dto.produit)
        assertEquals(entity.prix, dto.prix)
    }

    @Test
    fun dtoToEntity(){
        val dto = PropositionDTO(
            idSociete = 1,
            refProduit = "VisPRO",
            prix = BigDecimal(3200)
        )


        val entity = PropositionMapperImpl().toEntity(dto)

        assertEquals(dto.idSociete , entity.propositionKey!!.idSociete)
        assertEquals(dto.refProduit, entity.propositionKey!!.reference)
        assertEquals(dto.prix, entity.prix)
    }
}