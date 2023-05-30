package com.raminagrobis.centraleachat.usecase.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.DesactiverProduit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DesactiverProduitTest {

    @Mock
    private lateinit var repoProduit :IProduitRepo

    @InjectMocks
    private lateinit var useCase : DesactiverProduit

    @Test
    fun leProduitDoitEtreDesactive(){
        val ref = "abc"
        val produit = Produit(reference = ref, actif = false)

        Mockito.`when`(repoProduit.getProduitByRef(ref)).thenReturn(produit)

        useCase.handle(ref)

        produit.actif?.let { Assertions.assertFalse(it) }
        verify(repoProduit, times(1)).saveProduit(produit)
    }
}