package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.mapper.PropositionMapper
import org.springframework.stereotype.Repository

@Repository
class PropositionRepo(private val repo : SQLProposition, private val mapper : PropositionMapper) : IPropositionRepo {
    override fun saveProposition(proposition: PropositionDTO) {
        repo.save(mapper.toEntity(proposition))
    }


    override fun deleteProposition(proposition: PropositionDTO) {
        repo.delete(mapper.toEntity(proposition))
    }

    override fun getPropositionsByProduit(refProduit: String): Iterable<PropositionDTO> {
        return repo.getAllByProduitOrderByPrix(refProduit).map { mapper.toDTO(it) }
    }

    override fun getPropositionsBySociete(idSociete : Int): Iterable<PropositionDTO> {
        return repo.getAllBySocieteOrderByPrix(idSociete).map { mapper.toDTO(it) }
    }
}