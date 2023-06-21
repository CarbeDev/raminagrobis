package com.raminagrobis.centraleachat.domain.fournisseur.adapter

import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO

interface IPropositionRepo {
    fun saveProposition(proposition: PropositionDTO)
    fun deleteProposition(proposition: PropositionDTO)
    fun getPropositionsByProduit(refProduit: String): Iterable<PropositionDTO>
    fun getPropositionsBySociete(idSociete : Int): Iterable<PropositionDTO>

}