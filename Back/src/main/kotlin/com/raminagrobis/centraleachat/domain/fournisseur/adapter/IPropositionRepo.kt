package com.raminagrobis.centraleachat.domain.fournisseur.adapter

import com.raminagrobis.centraleachat.domain.fournisseur.model.Proposition

interface IPropositionRepo {
    fun saveProposition(proposition: Proposition)
    fun deleteProposition(proposition: Proposition)

}