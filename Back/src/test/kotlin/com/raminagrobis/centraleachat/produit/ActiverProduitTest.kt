package com.raminagrobis.centraleachat.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.ActiverProduit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ActiverProduitTest {

    @Mock
    private lateinit var repoProduit : IProduitRepo

    @InjectMocks
    private lateinit var usecase : ActiverProduit

    @Test
    fun leProduitDoitEtreActive(){
        val ref = "abcd"
        val produit = Produit(reference = ref,actif = false)

        Mockito.`when`(repoProduit.getProduitByRef(ref)).thenReturn(produit)
        usecase.handle(ref)

        produit.actif?.let { Assertions.assertTrue(it) }
        verify(repoProduit, times(1)).saveProduit(produit)
    }
}