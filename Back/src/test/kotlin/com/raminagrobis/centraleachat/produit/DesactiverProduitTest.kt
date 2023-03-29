package com.raminagrobis.centraleachat.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.DesactiverProduit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DesactiverProduitTest {

    @Mock
    private lateinit var repoProduit :IProduitRepo

    @InjectMocks
    private lateinit var useCase : DesactiverProduit

    @BeforeEach
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun TheProduitMustBeDesactive(){
        val ref = "abc"
        val produit = Produit(reference = ref, actif = false)

        Mockito.`when`(repoProduit.getProduitByRef(ref)).thenReturn(produit)

        useCase.handle(ref)

        produit.actif?.let { Assertions.assertFalse(it) }
        verify(repoProduit, times(1)).saveProduit(produit)
    }
}