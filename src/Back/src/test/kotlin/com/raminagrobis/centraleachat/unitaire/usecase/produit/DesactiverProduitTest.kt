package com.raminagrobis.centraleachat.unitaire.usecase.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.mapper.ProduitMapper
import com.raminagrobis.centraleachat.domain.administration.usecase.DesactiverProduit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DesactiverProduitTest {

    @Mock
    private lateinit var repoProduit :IProduitRepo
    @Spy
    private lateinit var mapper: ProduitMapper

    @InjectMocks
    private lateinit var useCase : DesactiverProduit

    @Test
    fun leProduitDoitEtreDesactive(){
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


        `when`(repoProduit.getProduitByRef(ref)).thenReturn(produit)

        useCase.handle(ref)

        Assertions.assertFalse(produit.actif)
        verify(repoProduit, times(1)).saveProduit(produit)
    }
}