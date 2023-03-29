package com.raminagrobis.centraleachat.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.model.Produit
import com.raminagrobis.centraleachat.domain.administration.usecase.AjouterUnProduit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AjouterUnProduitTest {

    @Mock
    private lateinit var repoProduit : IProduitRepo

    @InjectMocks
    private lateinit var useCase : AjouterUnProduit

    @BeforeEach
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun addAProduitMustSaveItOnce(){
        val produit = Produit()

        useCase.handle(produit)

        verify(repoProduit , times(1)).saveProduit(produit)
    }
}