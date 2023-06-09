package com.raminagrobis.centraleachat.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapperImpl
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProduitMapperTest {

    val dto = ProduitDTO(
        reference = "VisPRO",
        nom = "Apple Vision Pro",
        description = "Revolutionnaire",
        actif = true,
        CategorieDTO(
            id = 3,
            libelle = "Autre"
        )
    )

    val entity = ProduitEntity(
        reference = "AN1500",
        nom = "WD_BLACK AN1500 4To",
        description = "Rapide et efficace",
        actif = false,
        CategorieEntity(
            id = 4,
            libelle = "Stockage"
        )
    )

    @Test
    fun DTOtoEntity(){
        val entity = ProduitMapperImpl().toEntity(dto)

        assertEquals(dto.reference,entity.reference)
        assertEquals(dto.nom,entity.nom)
        assertEquals(dto.description,entity.description)
        assertEquals(dto.actif,entity.actif)
        assertEquals(dto.categorie.id,entity.categorie!!.id)
        assertEquals(dto.categorie.libelle,entity.categorie!!.libelle)
    }

    @Test
    fun EntitytoDTO(){
        val dto = ProduitMapperImpl().toDTO(entity)

        assertEquals(entity.reference,dto.reference)
        assertEquals(entity.nom,dto.nom)
        assertEquals(entity.description,dto.description)
        assertEquals(entity.actif,dto.actif)
        assertEquals(entity.categorie!!.id,dto.categorie.id)
        assertEquals(entity.categorie!!.libelle,dto.categorie.libelle)
    }
}