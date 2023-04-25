package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.data.repository.CrudRepository


interface SQLSociete : CrudRepository<Societe,Int>{
    fun existsByEmail(email : String) : Boolean
    fun getSocieteByEmail(email: String) : Societe
}