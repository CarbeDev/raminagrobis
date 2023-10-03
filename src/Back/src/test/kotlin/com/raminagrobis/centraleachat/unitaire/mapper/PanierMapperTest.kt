package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.mapper.PanierMapperImpl
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PanierMapperTest {

    @Test
    fun dtoToEntity(){
        val dto = PanierDTO(
            id = "01-23",
            listeAchat = listOf(),
            etatPanier = EtatPanier.FERMER
        )

        val entity = PanierMapperImpl().toEntity(dto)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.listeAchat, entity.listeAchat)
        assertEquals(dto.etatPanier, entity.etatPanier)
    }

    @Test
    fun entityToDto(){

        val entity = PanierEntity(
            id = "22-23",
            listeAchat = listOf(),
            etatPanier = EtatPanier.OUVERT
        )

        val dto = PanierMapperImpl().toDTO(entity)

        assertEquals(entity.id, dto.id)
        assertEquals(entity.listeAchat, dto.listeAchat)
        assertEquals(entity.etatPanier, dto.etatPanier)
    }
}