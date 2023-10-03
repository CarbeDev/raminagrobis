package com.raminagrobis.centraleachat.infra.utilisateur

import com.raminagrobis.centraleachat.infra.utilisateur.entity.AdminEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SQLAdmin : CrudRepository<AdminEntity,Int>{
    fun findAdminByEmail(email: String):Optional<AdminEntity>
}