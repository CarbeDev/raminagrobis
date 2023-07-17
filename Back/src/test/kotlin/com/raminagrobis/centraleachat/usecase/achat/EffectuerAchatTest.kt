package com.raminagrobis.centraleachat.usecase.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EffectuerAchatTest {

    @Mock
    private lateinit var repoAchat: IAchatRepo

    @InjectMocks
    private lateinit var useCase : EffectuerAchat

    private lateinit var panier : PanierDTO
    private lateinit var achat : AchatDTO

    @BeforeEach
    fun setup(){
        panier = PanierDTO(
            id = "23-23",
            etatPanier = EtatPanier.OUVERT,
            listeAchat = listOf()
        )

        achat = AchatDTO(
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
            panier = panier,
            quantite = 50
        )
    }


    @Test
    fun unAchatQuiEstEffectueParUnFournisseurDoitEnvoiUneException(){

        achat.adherent.role = Role.FOURNISSEUR

        assertThrows(IncorrectRoleSocieteException::class.java){
            useCase.handle(achat)
        }

    }

    @Test
    fun unAchatQuiConcerneUnPanierFermeEnvoiUneException(){
        achat.panier.etatPanier = EtatPanier.FERMER

        assertThrows(CantAddAchatException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatDUnAdherentDansUnPanierFermeDoitEtreSauvegarder(){
        useCase.handle(achat)

        verify(repoAchat, times(1)).saveAchat(achat)
    }
}