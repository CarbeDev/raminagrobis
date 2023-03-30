package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class FaireProposition(private val repo: IPropositionRepo) {

    fun handle(proposition: Proposition){
        if (proposition.prix!! <= BigDecimal(0)){
            throw PriceTooLowException()
        } else{
            repo.saveProposition(proposition)
        }
    }
}