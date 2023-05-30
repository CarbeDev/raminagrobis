package com.raminagrobis.centraleachat.infra.panier

import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import com.raminagrobis.centraleachat.domain.commande.model.Panier
import org.springframework.stereotype.Repository

@Repository
class PanierRepo(val repo: SQLPanier) : IPanierRepo{

    override fun getPaniersOuvert(): Iterable<Panier> {
        return repo.findAllByEtat(EtatPanier.OUVERT)
    }

    override fun savePanier(panier: Panier) {
       repo.save(panier)
    }


}