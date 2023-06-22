package com.raminagrobis.centraleachat.domain.commande.builder

import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.domain.commande.model.EtatPanier
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PanierBuilder {

    fun build(jour : Calendar): PanierDTO {
        val numSemaine = getNumSemaine(jour)
        val annee = getDeuxDerniersNombresDeLannee(jour)

        return PanierDTO(id = numSemaine + "_" + annee, listeAchat = listOf(), etatPanier = EtatPanier.OUVERT)
    }

    private fun getNumSemaine(jour: Calendar) : String{
        var numSemaine = jour.get(Calendar.WEEK_OF_YEAR).toString()

        if (numSemaine.length == 1) numSemaine = "0" + numSemaine

        return numSemaine
    }

    private fun getDeuxDerniersNombresDeLannee(jour: Calendar): String {
        val df: DateFormat = SimpleDateFormat("yy")
        return df.format(jour.time!!)
    }
}