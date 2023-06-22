package com.raminagrobis.centraleachat.domain.demande.adapter

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO

interface IDemandeInterface {
    fun saveDemande(demande: DemandeDTO)
}