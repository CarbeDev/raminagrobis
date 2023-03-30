package com.raminagrobis.centraleachat.proposition

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.FaireProposition
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class FairePropositionTest {

    @Mock
    private lateinit var propositionRepo : IPropositionRepo

    @InjectMocks
    private lateinit var usecase : FaireProposition

    @BeforeEach
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun aPropositionWhitPrixHigherThan0MustBeSave(){
        val proposition = Proposition(prix = BigDecimal(100))

        usecase.handle(proposition)
        verify(propositionRepo, times(1)).saveProposition(proposition)
    }

    @Test
    fun aPropositionWithPrixUnderOrEqualsTo0MustThrowAnException(){
        val proposition = Proposition(prix = BigDecimal(0))

        Assertions.assertThrows(PriceTooLowException::class.java){
            usecase.handle(proposition)
        }
    }
}