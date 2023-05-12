package com.raminagrobis.centraleachat.usecase.proposition

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.EnleverUnePropositionDePrix
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EnleverPropositionDePrixTest {

    @Mock
    private lateinit var repoProposition: IPropositionRepo

    @InjectMocks
    private lateinit var useCase : EnleverUnePropositionDePrix

    @Test
    fun laPropositionDoitEtreSupprime(){
        val proposition = Proposition()

        useCase.handle(proposition)

        verify(repoProposition, times(1)).deleteProposition(proposition)
    }
}