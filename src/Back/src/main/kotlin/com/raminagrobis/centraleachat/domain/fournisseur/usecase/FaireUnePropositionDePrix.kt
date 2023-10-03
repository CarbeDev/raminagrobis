package com.raminagrobis.centraleachat.domain.fournisseur.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.fournisseur.adapter.IPropositionRepo
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.exception.IncorrectRoleSocieteException
import com.raminagrobis.centraleachat.domain.fournisseur.exception.PriceTooLowException
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class FaireUnePropositionDePrix(private val repoProposition: IPropositionRepo, private val repoSociete : ISocieteRepo) {

    fun handle(proposition: PropositionDTO){

        val societe = repoSociete.findSocieteByID(proposition.idSociete)

        if (societe.role == Role.ADHERENT){
            throw IncorrectRoleSocieteException()
        } else if (proposition.prix <= BigDecimal(0)){
            throw PriceTooLowException()
        } else{
            repoProposition.saveProposition(proposition)
        }
    }
}