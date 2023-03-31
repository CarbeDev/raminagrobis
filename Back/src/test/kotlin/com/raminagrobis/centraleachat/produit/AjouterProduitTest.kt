package com.raminagrobis.centraleachat.produit

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.AjouterProduit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AjouterProduitTest {

    @Mock
    private lateinit var repoProduit : IProduitRepo

    @InjectMocks
    private lateinit var useCase : AjouterProduit

    @Test
    fun ajouterUnProduitDoitLeSauvegarder(){
        val produit = Produit()

        val produitCaptor = argumentCaptor<Produit>()

        useCase.handle(produit)

        verify(repoProduit , times(1)).saveProduit(produitCaptor.capture())
        Assertions.assertEquals(produit,produitCaptor.firstValue)
    }
}