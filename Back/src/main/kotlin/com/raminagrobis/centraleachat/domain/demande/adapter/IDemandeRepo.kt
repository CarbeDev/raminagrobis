package com.raminagrobis.centraleachat.domain.demande.adapter

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO

interface IDemandeRepo {
    fun saveDemande(demande: DemandeDTO)
    fun getDemandeById(id : Int) : DemandeDTO
    fun getDemandeBySociete(id : Int) : Iterable<DemandeDTO>
    fun getDemandes(): Iterable<DemandeDTO>
}