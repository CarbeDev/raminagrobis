package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.domain.administration.mapper.SocieteMapperImpl
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class SocieteMapperTest {

    val entity = SocieteEntity(
        id = 1,
        nom = "raminagrobis",
        email = "raminagrobis@test.fr",
        motDePasse = "motDePasse",
        role = Role.FOURNISSEUR,
        actif = true,
        dateInscription = java.sql.Date(0),
    )

    val dto = SocieteDTO(
        id = 2,
        nom = "Best Buy",
        email = "bestbuy@adherent.com",
        role = Role.ADHERENT,
        actif = true
    )

    val detailSociete = DetailSociete(
        id = 3,
        nom = "Lidl",
        email = "lidl@adherent.com",
        role = Role.ADHERENT,
        actif = false,
        dateInscription = Date(),
    )

    val userSociete = UserSociete(
        nom = "Carrefour",
        email = "carrefour@adherent.fr",
        motDePasse = "MotDePasse",
        role = Role.ADHERENT,
        dateInscription = Date(),
        actif = true
    )

    @Test
    fun entityToDTO(){
        val dto = SocieteMapperImpl().toDTO(entity)

        assertEquals(entity.id,dto.id)
        assertEquals(entity.nom, dto.nom)
        assertEquals(entity.email, dto.email)
        assertEquals(entity.role, dto.role)
        assertEquals(entity.actif, dto.actif)
    }

    @Test
    fun entityToDetail(){
        val detail = SocieteMapperImpl().toDetail(entity)

        assertEquals(entity.id,detail.id)
        assertEquals(entity.nom, detail.nom)
        assertEquals(entity.email, detail.email)
        assertEquals(entity.role, detail.role)
        assertEquals(entity.actif, detail.actif)
        assertEquals(entity.dateInscription,detail.dateInscription)
    }

    @Test
    fun dtoToEntity(){
        val entity = SocieteMapperImpl().toEntity(dto)

        assertEquals(dto.id,entity.id)
        assertEquals(dto.nom,entity.nom)
        assertEquals(dto.email,entity.email)
        assertEquals(dto.role,entity.role)
        assertEquals(dto.actif,entity.actif)
    }

    @Test
    fun detailSocieteToEntity(){
        val entity = SocieteMapperImpl().toEntity(detailSociete)

        assertEquals(detailSociete.id,entity.id)
        assertEquals(detailSociete.nom,entity.nom)
        assertEquals(detailSociete.email,entity.email)
        assertEquals(detailSociete.role,entity.role)
        assertEquals(detailSociete.dateInscription, entity.dateInscription)
        assertEquals(detailSociete.actif,entity.actif)
    }

    @Test
    fun userSocieteToEntity(){
        val entity = SocieteMapperImpl().toEntity(userSociete)

        assertEquals(userSociete.nom,entity.nom)
        assertEquals(userSociete.email,entity.email)
        assertEquals(userSociete.motDePasse,entity.motDePasse)
        assertEquals(userSociete.role,entity.role)
        assertEquals(userSociete.dateInscription, entity.dateInscription)
        assertEquals(userSociete.actif,entity.actif)
    }
}