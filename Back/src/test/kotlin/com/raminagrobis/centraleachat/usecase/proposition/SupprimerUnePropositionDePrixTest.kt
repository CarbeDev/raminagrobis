package com.raminagrobis.centraleachat.usecase.proposition

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.usecase.SupprimerUnePropositionDePrix
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SupprimerUnePropositionDePrixTest {

    @Mock
    private lateinit var repoProposition: IPropositionRepo

    @InjectMocks
    private lateinit var useCase : SupprimerUnePropositionDePrix

    @Test
    fun laPropositionDoitEtreSupprime(){
        val key = PropositionKey(
            reference = "VisPro",
            idSociete = 1
        )

        useCase.handle(key)

        verify(repoProposition, times(1)).deleteProposition(key)
    }
}