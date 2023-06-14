package com.raminagrobis.centraleachat.domain.administration.adapter

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity


interface ISocieteRepo {

    fun getAll() : Iterable<SocieteDTO>
    fun getByEmail(email: String) : SocieteEntity
    fun saveSociete(societe: SocieteDTO)
    fun saveSociete(societe : DetailSociete)
    fun saveSociete(societe : UserSociete)
    fun isEmailUnique(email: String) : Boolean
    fun findSocieteByID(id : Int) : DetailSociete

    fun deleteSociete(societe: SocieteDTO)

    fun deleteSociete(societe: DetailSociete)

}
