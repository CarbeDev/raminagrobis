package com.raminagrobis.centraleachat.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapperImpl
import com.raminagrobis.centraleachat.domain.administration.mapper.SocieteMapperImpl
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.mapper.AchatMapperImpl
import com.raminagrobis.centraleachat.domain.commande.mapper.PanierMapperImpl
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AchatMapperTest {

    @Test
    fun entitytoDTO(){

        val idSociete = 1
        val reference = "L5490"
        val idPanier = "23-23"

        val entity = AchatEntity(
            key = AchatKey(
                idSociete,
                reference,
                idPanier
            ),
            adherent = SocieteEntity(
                id = idSociete,
                nom = "Zach Nani Production",
                email = "zachnani@production.fr",
                motDePasse = "TwitchFr",
                role = Role.ADHERENT,
                actif = true,
                historique = listOf()
            ),
            produit = ProduitEntity(
                reference = reference,
                nom = "Dell Latitude 5490",
                description = "Un ordinateur portable",
                actif = true,
                categorie = CategorieEntity(
                    id = 6,
                    libelle = "Ordinateur portable"
                )
            ),
            panier = PanierEntity(
                id = idPanier,
                listeAchat = listOf(),
                etatPanier = EtatPanier.OUVERT
            ),
            quantite = 150
        )

        val dto = AchatMapperImpl().toDTO(entity)

        assertEquals(SocieteMapperImpl().toDTO(entity.adherent!!),dto.adherent)
        assertEquals(ProduitMapperImpl().toDTO(entity.produit!!),dto.produit)
        assertEquals(PanierMapperImpl().toDTO(entity.panier!!),dto.panier)
        assertEquals(entity.quantite,dto.quantite)
    }

    @Test
    fun dtoToEntity(){

        val dto = AchatDTO(
            adherent = SocieteDTO(
                id = 1,
                nom = "Zach Nani Production",
                email = "zachnani@production.fr",
                role = Role.ADHERENT,
                actif = true,
            ),
            produit = ProduitDetail(
                reference = "L5490",
                nom = "Dell Latitude 5490",
                description = "Un ordinateur portable",
                actif = true,
                categorie = CategorieDTO(
                    id = 6,
                    libelle = "Ordinateur portable"
                )
            ),
            panier = PanierDTO(
                id = "23-23",
                listeAchat = listOf(),
                etatPanier = EtatPanier.OUVERT
            ),
            quantite = 75
        )

        val entity = AchatMapperImpl().toEntity(dto)

        assertEquals(dto.adherent.id,entity.key!!.idSociete)
        assertEquals(dto.panier.id,entity.key!!.idpanier)
        assertEquals(dto.produit.reference,entity.key!!.reference)
        assertEquals(dto.quantite,entity.quantite)
    }
}