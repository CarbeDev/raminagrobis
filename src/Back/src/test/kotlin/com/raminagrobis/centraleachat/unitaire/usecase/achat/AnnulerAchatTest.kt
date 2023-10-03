package com.raminagrobis.centraleachat.unitaire.usecase.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.exception.CantRemoveAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AnnulerAchatTest {

    @Mock
    private lateinit var repo: IAchatRepo

    @InjectMocks
    private lateinit var usecase : AnnulerAchat

    private lateinit var key : AchatKey
    private lateinit var achat: AchatDetail

    @BeforeEach
    fun setup(){

        key = AchatKey(
            idSociete = 1,
            reference = "B550MDS3H",
            idPanier = "23-23"
        )

        achat = AchatDetail(
            adherent = SocieteDTO(
                id = 1,
                nom = "Free agent",
                email = "Freeagent@Adherent.com",
                role = Role.ADHERENT,
                actif = true
            ),
            produit = ProduitDetail(
                reference = "B550MDS3H",
                nom = "Gigabyte B550M DS3H",
                description = "Une carte mere",
                categorie = CategorieDTO(
                    id=1,
                    libelle = "Carte mere"),
                actif = true,
            ),
            panier = PanierDTO(
                id = "23-23",
                etatPanier = EtatPanier.OUVERT,
                listeAchat = listOf()
            ),
            quantite = 50
        )

        `when`(repo.getAchat(key)).thenReturn(achat)
    }


    @Test
    fun annulerUnAchatAlorsQueLePanierEstFermeEnvoiUneException(){
        achat.panier.etatPanier = EtatPanier.FERMER



        assertThrows(CantRemoveAchatException::class.java){
            usecase.handle(key)
        }
    }

    @Test
    fun annulerUnAchatDansUnPanierOuvertLeSupprime(){

        usecase.handle(key)

        verify(repo, times(1)).deleteAchat(achat)
    }
}