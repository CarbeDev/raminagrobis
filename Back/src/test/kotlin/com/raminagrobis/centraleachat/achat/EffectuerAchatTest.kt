package com.raminagrobis.centraleachat.achat

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
        var societe = Societe(role = Role.FOURNISSEUR)
        var panier = Panier(etat = Etat.OUVERT)

        var achat = Achat(societe = societe, panier = panier)

        assertThrows(IncorrectRoleSocieteException::class.java){
            useCase.handle(achat)
        }
    }

    @Test
    fun unAchatQuiConcerneUnPanierFermeEnvoiUneException(){
        var societe = Societe(role = Role.ADHERENT)
        var panier = Panier(etat = Etat.FERMER)

        var achat = Achat(societe = societe,panier = panier)

        assertThrows(CantAddAchatException::class.java){
            useCase.handle(achat)
        }
    }
}