package com.raminagrobis.centraleachat.usecase.demande

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.usecase.RecupererDemandeParSociete
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RecupererDemandesParSocieteTest {

    @Mock
    private lateinit var repo: IDemandeRepo
    @InjectMocks
    private lateinit var usecase : RecupererDemandeParSociete


    @Test
    fun laFonctionDoitRetournerLesDemandes(){

        val demandes = listOf(
            DemandeDTO(
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
            ),
            DemandeDTO(
                id = 2,
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                categorie =CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                ),
                societe = SocieteDTO(
                    id = 1,
                    nom = "Fournisseur1",
                    email = "fournisseur1@email.fr",
                    role = Role.FOURNISSEUR,
                    actif = false
                )
            )
        )

        Mockito.`when`(repo.getDemandesBySociete(1)).thenReturn(demandes)

        Assertions.assertEquals(demandes, usecase.handle(1))
        verify(repo, times(1)).getDemandesBySociete(1)
    }
}