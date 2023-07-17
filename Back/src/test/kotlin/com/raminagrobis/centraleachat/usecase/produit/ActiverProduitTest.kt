package com.raminagrobis.centraleachat.usecase.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
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
        val ref = "LSD"
        val produit = ProduitDetail(
            reference = ref,
            nom = "Logitech StreamDeck",
            description = "Tres utile",
            categorie = CategorieDTO(
                id = 3,
                libelle = "Autre"
            ),
            actif = false
        )

        Mockito.`when`(repoProduit.getProduitByRef(ref)).thenReturn(produit)
        usecase.handle(ref)

        Assertions.assertTrue(produit.actif)
        verify(repoProduit, times(1)).saveProduit(produit)
    }
}