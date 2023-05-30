package com.raminagrobis.centraleachat.infra.utilisateur

import com.raminagrobis.centraleachat.domain.administration.model.Admin
import org.springframework.data.repository.CrudRepository

interface SQLAdmin : CrudRepository<Admin,Int>{
    fun findAdminByEmail(email: String):Admin?
}