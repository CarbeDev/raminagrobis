package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapperImpl
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProduitMapperTest {

    val detail = ProduitDetail(
        reference = "VisPRO",
        nom = "Apple Vision Pro",
        description = "Revolutionnaire",
        actif = true,
        categorie = CategorieDTO(
            id = 3,
            libelle = "Autre"
        )
    )

    val entity = ProduitEntity(
        reference = "AN1500",
        nom = "WD_BLACK AN1500 4To",
        description = "Rapide et efficace",
        actif = false,
        categorie = CategorieEntity(
            id = 4,
            libelle = "Stockage"
        )
    )

    val dto = ProduitDTO(
        reference = "VisPRO",
        nom = "Apple Vision Pro",
        description = "Revolutionnaire",
        actif = true,
        idCategorie = 4
    )

    @Test
    fun detailToEntity(){
        val entity = ProduitMapperImpl().toEntity(detail)

        assertEquals(detail.reference,entity.reference)
        assertEquals(detail.nom,entity.nom)
        assertEquals(detail.description,entity.description)
        assertEquals(detail.actif,entity.actif)
        assertEquals(detail.categorie.id,entity.categorie!!.id)
        assertEquals(detail.categorie.libelle,entity.categorie!!.libelle)
    }

    @Test
    fun entityToDetail(){
        val dto = ProduitMapperImpl().toDTO(entity)

        assertEquals(entity.reference,dto.reference)
        assertEquals(entity.nom,dto.nom)
        assertEquals(entity.description,dto.description)
        assertEquals(entity.actif,dto.actif)
        assertEquals(entity.categorie!!.id,dto.categorie.id)
        assertEquals(entity.categorie!!.libelle,dto.categorie.libelle)
    }

    @Test
    fun dtoToEntity(){
        val entity = ProduitMapperImpl().toEntity(dto)

        assertEquals(dto.reference,entity.reference)
        assertEquals(dto.nom,entity.nom)
        assertEquals(dto.description,entity.description)
        assertEquals(dto.actif,entity.actif)
        assertEquals(dto.idCategorie,entity.categorie!!.id)
    }
}