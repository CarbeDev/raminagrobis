package com.raminagrobis.centraleachat.infra

import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SocieteRepo : JpaRepository<Societe,Int>{
    fun existsByEmail(email : String) : Boolean

}