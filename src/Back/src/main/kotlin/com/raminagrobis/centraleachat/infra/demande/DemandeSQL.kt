package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.infra.demande.entity.DemandeEntity
import org.springframework.data.repository.CrudRepository

interface DemandeSQL : CrudRepository<DemandeEntity,Int>{

    fun findAllBySocieteId(id : Int): Iterable<DemandeEntity>
}