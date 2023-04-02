package com.raminagrobis.centraleachat.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.exception.CantAddAchatException
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.Etat
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import com.raminagrobis.centraleachat.domain.commande.usecase.EffectuerAchat
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
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
        val societe = Societe(role = Role.FOURNISSEUR)
        val panier = Panier(etat = Etat.OUVERT)

        val achat = Achat(societe = societe, panier = panier)

        assertThrows(IncorrectRoleSocieteException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatQuiConcerneUnPanierFermeEnvoiUneException(){
        val societe = Societe(role = Role.ADHERENT)
        val panier = Panier(etat = Etat.FERMER)

        val achat = Achat(societe = societe,panier = panier)

        assertThrows(CantAddAchatException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun UnAchatDUnAdherentDansUnPanierFermeDoitEtreSauvegarder(){
        val societe = Societe(role = Role.ADHERENT)
        val panier = Panier(etat = Etat.OUVERT)

        val achat = Achat(societe = societe,panier = panier)

        useCase.handle(achat)

        verify(repoAchat, times(1)).saveAchat(achat)
    }
}