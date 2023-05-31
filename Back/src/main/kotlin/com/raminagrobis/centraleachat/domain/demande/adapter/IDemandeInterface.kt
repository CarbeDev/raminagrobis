package com.raminagrobis.centraleachat.domain.demande.adapter

import com.raminagrobis.centraleachat.domain.demande.model.Demande

interface IDemandeInterface {
    fun saveDemande(demande: Demande)
}