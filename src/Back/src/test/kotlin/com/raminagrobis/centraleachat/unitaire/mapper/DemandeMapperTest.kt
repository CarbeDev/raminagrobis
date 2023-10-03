package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.mapper.CategoryMapperImpl
import com.raminagrobis.centraleachat.domain.administration.mapper.SocieteMapperImpl
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail
import com.raminagrobis.centraleachat.domain.demande.mapper.DemandeMapperImpl
import com.raminagrobis.centraleachat.infra.demande.entity.DemandeEntity
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DemandeMapperTest {

    @Test
    fun entityToDTO(){
        val entity = DemandeEntity(
            id = 1,
            nom = "Apple TrackPad",
            description = "Trop cher",
            categorie = CategorieEntity(
                id = 1,
                libelle = "Souris"
            ),
            societe = SocieteEntity(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false,
            )
        )

        val dto = DemandeMapperImpl().toDTO(entity)

        assertEquals(entity.id, dto.id)
        assertEquals(entity.nom, dto.nom)
        assertEquals(CategoryMapperImpl().toDTO(entity.categorie!!), dto.categorie)
        assertEquals(SocieteMapperImpl().toDTO(entity.societe!!), dto.societe)
        assertEquals(entity.description, dto.description)
        assertEquals(entity.etat, dto.etat)
    }

    @Test
    fun dtoToEntity(){

        val dto = DemandeDetail(
            id = 1,
            nom = "Apple TrackPad",
            description = "Trop cher",
            categorie = CategorieDTO(
                id = 1,
                libelle = "Souris"
            ),
            societe = SocieteDTO(
                id = 1,
                nom = "Fournisseur1",
                email = "fournisseur1@email.fr",
                role = Role.FOURNISSEUR,
                actif = false
            )
        )

        val entity = DemandeMapperImpl().toEntity(dto)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.etat, entity.etat)
        assertEquals(dto.nom, entity.nom)
        assertEquals(dto.societe.id,entity.societe!!.id)
        assertEquals(dto.categorie.id, entity.categorie!!.id)
        assertEquals(dto.description, entity.description)
    }
}