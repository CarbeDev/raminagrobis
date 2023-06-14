package com.raminagrobis.centraleachat.usecase.demande

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeInterface
import com.raminagrobis.centraleachat.domain.demande.model.Demande
import com.raminagrobis.centraleachat.domain.demande.usecase.FaireDemande
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FaireDemandeTest {

    @Mock
    private lateinit var repo : IDemandeInterface

    @InjectMocks
    private lateinit var usecase : FaireDemande

    @Test
    fun uneDemandeDoitEtreEnregistre(){

        val demande = Demande(
            id = 0,
            societe = SocieteEntity(),
            nom = "Apple TrackPad",
            categorie = CategorieEntity(
                id = 1,
                libelle = "Souris"
            ),
            description = "Un peu trop cher"
        )

        usecase.handle(demande)

        verify(repo, times(1)).saveDemande(demande)
    }
}