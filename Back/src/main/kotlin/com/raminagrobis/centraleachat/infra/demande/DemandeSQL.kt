package com.raminagrobis.centraleachat.infra.demande

import com.raminagrobis.centraleachat.domain.demande.model.Demande
import org.springframework.data.repository.CrudRepository

interface DemandeSQL : CrudRepository<Demande,Int>{
}