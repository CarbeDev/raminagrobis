package com.raminagrobis.centraleachat.domain.demande.adapter

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO

interface IDemandeRepo {
    fun saveDemande(demande: DemandeDTO)
    fun getDemande(id : Int) : DemandeDTO
    fun getDemandes(): Iterable<DemandeDTO>
}