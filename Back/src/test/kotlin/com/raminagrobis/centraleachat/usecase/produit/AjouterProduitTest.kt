package com.raminagrobis.centraleachat.usecase.produit

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.administration.adapter.IProduitRepo
import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.domain.administration.usecase.AjouterProduit
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
        val produit = ProduitDTO(
            reference = "LSD",
            nom = "Logitech StreamDeck",
            description = "Tres utile",
            idCategorie = 3,
            actif = false
        )

        useCase.handle(produit)

        verify(repoProduit , times(1)).saveProduit(produit)

    }
}