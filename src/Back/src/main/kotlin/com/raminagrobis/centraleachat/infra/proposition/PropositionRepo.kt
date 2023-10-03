package com.raminagrobis.centraleachat.infra.proposition

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.domain.fournisseur.mapper.PropositionMapper
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionKey
import org.springframework.stereotype.Repository

@Repository
class PropositionRepo(private val repo : SQLProposition, private val mapper : PropositionMapper) : IPropositionRepo {
    override fun saveProposition(proposition: PropositionDTO) {
        repo.save(mapper.toEntity(proposition))
    }


    override fun deleteProposition(id : PropositionKey) {
        repo.deleteById(id)
    }

    override fun getPropositionsByProduit(refProduit: String): Iterable<PropositionDetail> {
        return repo.getAllByPropositionKeyReferenceOrderByPrix(refProduit).map { mapper.toDTO(it) }
    }

    override fun getPropositionsBySociete(idSociete : Int): Iterable<PropositionDetail> {
        return repo.getAllByPropositionKeyIdSociete(idSociete).map { mapper.toDTO(it) }
    }

    override fun getLowestPropositionPrixByPrix(refProduit: String): PropositionDetail {
        return mapper.toDTO(repo.getFirstByPropositionKeyReferenceOrderByPrix(refProduit))
    }
}