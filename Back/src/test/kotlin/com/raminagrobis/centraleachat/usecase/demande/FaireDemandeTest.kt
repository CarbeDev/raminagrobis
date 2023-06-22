package com.raminagrobis.centraleachat.usecase.demande

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.usecase.FaireDemande
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FaireDemandeTest {

    @Mock
    private lateinit var repo : IDemandeRepo

    @InjectMocks
    private lateinit var usecase : FaireDemande

    @Test
    fun uneDemandeDoitEtreEnregistre(){

        val demande = DemandeDTO(
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

        usecase.handle(demande)

        verify(repo, times(1)).saveDemande(demande)
    }
}