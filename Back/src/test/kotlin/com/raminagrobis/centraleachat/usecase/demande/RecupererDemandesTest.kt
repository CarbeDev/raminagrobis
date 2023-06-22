package com.raminagrobis.centraleachat.usecase.demande

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.raminagrobis.centraleachat.domain.demande.adapter.IDemandeRepo
import com.raminagrobis.centraleachat.domain.demande.usecase.RecupererDemandes
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RecupererDemandesTest {

    @Mock
    private lateinit var repo : IDemandeRepo

    @InjectMocks
    private lateinit var usecase : RecupererDemandes

    @Test
    fun laFonctionDoitRetournerLesDemandes(){
        usecase.handle()

        verify(repo, times(1)).getDemandes()
    }
}