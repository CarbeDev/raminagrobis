package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.data.repository.CrudRepository


interface SQLSociete : CrudRepository<Societe,Int>{
    fun existsByEmail(email : String) : Boolean

}