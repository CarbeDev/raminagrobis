package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.domain.commande.mapper.AchatMapper
import com.raminagrobis.centraleachat.infra.achat.entity.AchatKey
import org.springframework.stereotype.Repository

@Repository
class AchatRepo(val repo : SQLAchat, val mapper : AchatMapper) : IAchatRepo{
    override fun getAllAchat(): Iterable<AchatDetail> {
        return repo.findAll().map { mapper.toDetail(it) }
    }

    override fun getAchatsByCommande(idPanier : String): Iterable<AchatDetail> {
        return repo.getAchatsByPanierId(idPanier).map { mapper.toDetail(it) }
    }

    override fun getAchatsByProduit(refProduit: String): Iterable<AchatDetail> {
        return repo.getAchatsByProduitReference(refProduit).map { mapper.toDetail(it) }
    }

    override fun getAchatsByProduitAndCommande(refProduit: String, idPanier: String) : Iterable<AchatDetail>{
        return repo.getAchatsByProduitReferenceAndPanierId(refProduit, idPanier).map { mapper.toDetail(it) }
    }

    override fun getAchat(key: AchatKey): AchatDetail {
        return mapper.toDetail(repo.getAchatByProduitReferenceAndPanierIdAndAdherentId(key.reference,key.idPanier,key.idSociete))
    }

    override fun saveAchat(achat: AchatMin) {
        repo.save(mapper.toEntity(achat))
    }

    override fun deleteAchat(achat: AchatDetail) {
       repo.delete(mapper.toEntity(achat))
    }

    override fun getNbAchatBySociete(societe: DetailSociete): Int {
        return if (societe.role == Role.FOURNISSEUR){
            repo.countByFournisseurId(societe.id)
        } else {
            repo.countByAdherentId(societe.id)
        }
    }
}