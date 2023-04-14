package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import org.springframework.stereotype.Repository

@Repository
class AchatRepo(val repo : SQLAchat) : IAchatRepo{
    override fun getAllAchat(): Iterable<Achat> {
        return repo.findAll()
    }

    override fun getAchatByCommande(idPanier : String): Iterable<Achat> {
        return repo.getAchatsByPanier_Id(idPanier)
    }

    override fun getAchatByProduit(refProduit: String): Iterable<Achat> {
        return repo.getAchatsByProduit(refProduit)
    }

    override fun getAchatByProduitAndCommande(refProduit: String, idPanier: String) {
        return repo.getAchatsByProduitAndPanier(refProduit, idPanier)
    }

    override fun saveAchat(achat: Achat) {
        repo.save(achat)
    }

    override fun deleteAchat(achat: Achat) {
       repo.delete(achat)
    }
}