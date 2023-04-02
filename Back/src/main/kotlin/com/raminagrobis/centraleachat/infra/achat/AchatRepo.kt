package com.raminagrobis.centraleachat.infra.achat

import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import com.raminagrobis.centraleachat.domain.commande.model.Achat
import org.springframework.stereotype.Repository

@Repository
class AchatRepo(val repo : SQLAchat) : IAchatRepo{
    override fun saveAchat(achat: Achat) {
        repo.save(achat)
    }

    override fun deleteAchat(achat: Achat) {
       repo.delete(achat)
    }
}