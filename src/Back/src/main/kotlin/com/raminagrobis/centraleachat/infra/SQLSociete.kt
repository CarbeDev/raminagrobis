package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.springframework.data.repository.CrudRepository
import java.util.*


interface SQLSociete : CrudRepository<SocieteEntity,Int>{
    fun existsByEmail(email : String) : Boolean
    fun getSocieteByEmail(email: String) : Optional<SocieteEntity>
}