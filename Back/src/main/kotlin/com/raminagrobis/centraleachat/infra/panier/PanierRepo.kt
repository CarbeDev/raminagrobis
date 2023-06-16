package com.raminagrobis.centraleachat.infra.panier

import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.mapper.PanierMapper
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import org.springframework.stereotype.Repository

@Repository
class PanierRepo(val repo: SQLPanier, val mapper : PanierMapper) : IPanierRepo{

    override fun getPaniersOuvert(): Iterable<PanierDTO> {
        return repo.findAllByEtatPanier(EtatPanier.OUVERT).map { mapper.toDTO(it) }
    }

    override fun savePanier(panier: PanierDTO) {
       repo.save(mapper.toEntity(panier))
    }


}