package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.mapper.AchatMapper
import org.springframework.stereotype.Repository

@Repository
class AchatRepo(val repo : SQLAchat, val mapper : AchatMapper) : IAchatRepo{
    override fun getAllAchat(): Iterable<AchatDTO> {
        return repo.findAll().map { mapper.toDTO(it) }
    }

    override fun getAchatByCommande(idPanier : String): Iterable<AchatDTO> {
        return repo.getAchatsByPanier_Id(idPanier).map { mapper.toDTO(it) }
    }

    override fun getAchatByProduit(refProduit: String): Iterable<AchatDTO> {
        return repo.getAchatsByProduit(refProduit).map { mapper.toDTO(it) }
    }

    override fun getAchatByProduitAndCommande(refProduit: String, idPanier: String) : Iterable<AchatDTO>{
        return repo.getAchatsByProduitAndPanier(refProduit, idPanier).map { mapper.toDTO(it) }
    }

    override fun saveAchat(achat: AchatDTO) {
        repo.save(mapper.toEntity(achat))
    }

    override fun deleteAchat(achat: AchatDTO) {
       repo.delete(mapper.toEntity(achat))
    }
}