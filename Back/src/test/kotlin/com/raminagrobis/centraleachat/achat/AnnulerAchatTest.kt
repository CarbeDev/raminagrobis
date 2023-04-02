package com.raminagrobis.centraleachat.achat

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.exception.CantRemoveAchatException
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import com.raminagrobis.centraleachat.domain.commande.model.Etat
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import com.raminagrobis.centraleachat.domain.commande.usecase.AnnulerAchat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AnnulerAchatTest {

    @Mock
    private lateinit var repoAchat: IAchatRepo

    @InjectMocks
    private lateinit var usecase : AnnulerAchat

    @Test
    fun annulerUnAchatAlorsQueLePanierEstFermeEnvoiUneException(){
        val panier = Panier(etat = Etat.FERMER)
        val achat = Achat(panier = panier)


        assertThrows(CantRemoveAchatException::class.java){
            usecase.handle(achat)
        }
    }

    @Test
    fun annulerUnAchatDansUnPanierOuvertLeSupprime(){
        val panier = Panier(etat = Etat.OUVERT)
        val achat = Achat(panier = panier)

        usecase.handle(achat)

        verify(repoAchat, times(1)).deleteAchat(achat)
    }
}