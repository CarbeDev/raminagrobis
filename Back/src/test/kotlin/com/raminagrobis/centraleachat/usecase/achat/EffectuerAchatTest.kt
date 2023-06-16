package com.raminagrobis.centraleachat.usecase.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.junit.jupiter.api.Assertions.*
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

    @Test
    fun unAchatQuiEstEffectueParUnFournisseurDoitEnvoiUneException(){
        val societe = SocieteEntity(role = Role.FOURNISSEUR)
        val panier = PanierEntity(etatPanier = EtatPanier.OUVERT)

        val achat = Achat(societe = societe, panier = panier)

        assertThrows(IncorrectRoleSocieteException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatQuiConcerneUnPanierFermeEnvoiUneException(){
        val societe = SocieteEntity(role = Role.ADHERENT)
        val panier = PanierEntity(etatPanier = EtatPanier.FERMER)

        val achat = Achat(societe = societe,panier = panier)

        assertThrows(CantAddAchatException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatDUnAdherentDansUnPanierFermeDoitEtreSauvegarder(){
        val societe = SocieteEntity(role = Role.ADHERENT)
        val panier = PanierEntity(etatPanier = EtatPanier.OUVERT)

        val achat = Achat(societe = societe,panier = panier)

        useCase.handle(achat)

        verify(repoAchat, times(1)).saveAchat(achat)
    }
}