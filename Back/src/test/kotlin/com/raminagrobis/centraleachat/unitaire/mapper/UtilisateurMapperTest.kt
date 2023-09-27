package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.mapper.UtilisateurMapperImpl
import com.raminagrobis.centraleachat.infra.utilisateur.entity.AdminEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UtilisateurMapperTest {

    @Test
    fun adminEntityToUtilisateur(){

        val entity = AdminEntity(
            id = 1,
            email = "fred@raminagrobis.fr",
            motDePasse = "Simplon"
        )

        val utilisateur = UtilisateurMapperImpl().toUtilisateur(entity)

        assertEquals(entity.email,utilisateur.email)
        assertEquals(entity.motDePasse, utilisateur.motDePasse)
        assertEquals(entity.role, utilisateur.role)
    }

    @Test
    fun societeEntityToUtilisateur(){

        val entity = SocieteEntity(
            id = 2,
            nom = "Casino",
            email = "casion@adherent.com",
            motDePasse = "MotDePasse",
            role = Role.ADHERENT,
            actif = true
        )

        assertEquals(entity.email,entity.email)
        assertEquals(entity.motDePasse, entity.motDePasse)
        assertEquals(entity.role, entity.role)
    }
}