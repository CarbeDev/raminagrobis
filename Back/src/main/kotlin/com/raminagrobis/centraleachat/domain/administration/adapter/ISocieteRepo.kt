package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.model.Societe


interface ISocieteRepo {

    fun getAll() : Iterable<Societe>
    fun getByEmail(email: String) : Societe
    fun saveSociete(societe: Societe)
    fun isEmailUnique(email: String) : Boolean
    fun findSocieteByID(id : Int) : Societe

    fun getNbCommandeBySociete(societe: Societe) : Int
    fun deleteSociete(societe: Societe)

}
