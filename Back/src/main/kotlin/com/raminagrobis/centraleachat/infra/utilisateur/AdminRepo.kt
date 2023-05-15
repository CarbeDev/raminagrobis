package com.raminagrobis.centraleachat.infra.utilisateur

import com.raminagrobis.centraleachat.domain.administration.model.Admin
import org.springframework.stereotype.Repository

@Repository
class AdminRepo(val repo : SQLAdmin){
    fun getByEmail(email : String) : Admin?{
        return repo.findAdminByEmail(email)
    }
}