package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.model.Societe
import java.util.Optional


interface ISocieteRepo {

    fun getAll() : Iterable<Societe>
    fun saveSociete(societe: Societe)
    fun isEmailUnique(email: String) : Boolean
    fun findUserByID(id : Int) : Optional<Societe>

    fun getNbCommandeBySociete(societe: Societe) : Int
    fun deleteSociete(societe: Societe)

}
