package com.raminagrobis.centraleachat.domain.demande.adapter

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail

interface IDemandeRepo {
    fun saveDemande(demande: DemandeDetail)
    fun saveDemande(demandeDTO: DemandeDTO)
    fun getDemandeById(id : Int) : DemandeDetail
    fun getDemandesBySociete(id : Int) : Iterable<DemandeDetail>
    fun getDemandes(): Iterable<DemandeDetail>
}