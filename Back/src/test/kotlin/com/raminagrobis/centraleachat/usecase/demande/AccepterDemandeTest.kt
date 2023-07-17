package com.raminagrobis.centraleachat.usecase.demande

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeGere
import com.raminagrobis.centraleachat.domain.demande.model.EtatDemande
import com.raminagrobis.centraleachat.domain.demande.usecase.AccepterDemande
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AccepterDemandeTest {

    @Mock
    private lateinit var demandeRepo: IDemandeRepo
    @Mock
    private lateinit var produitRepo: IProduitRepo
    @InjectMocks
    private lateinit var usecase : AccepterDemande

    private lateinit var demandeGere: DemandeGere



    @BeforeEach
    fun setup(){
        demandeGere = DemandeGere(
            produit = ProduitDetail(
                reference = "VisPRO",
                nom = "Apple Vision Pro",
                description = "Revolutionnaire",
                actif = true,
                CategorieDTO(
                    id = 3,
                    libelle = "Autre"
                )
            ),
            idDemande = 1
        )


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

        `when`(demandeRepo.getDemandeById(1)).thenReturn(demande)
        usecase.handle(demandeGere)
    }

    @Test
    fun unProduitEstCree(){
        verify(produitRepo, times(1)).saveProduit(demandeGere.produit)
    }

    @Test
    fun laDemandeEstMiseAJour(){

        val captor = argumentCaptor<DemandeDTO>()

        verify(demandeRepo, times(1)).saveDemande(captor.capture())
        assertEquals(EtatDemande.ACCEPTE, captor.firstValue.etat)
    }

}