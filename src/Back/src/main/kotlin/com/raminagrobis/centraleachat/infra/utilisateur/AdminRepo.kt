package com.raminagrobis.centraleachat.infra.utilisateur

import com.raminagrobis.centraleachat.infra.utilisateur.entity.AdminEntity
import org.springframework.stereotype.Repository

@Repository
class AdminRepo(val repo : SQLAdmin){
    fun getByEmail(email : String) : AdminEntity{
        return repo.findAdminByEmail(email).orElseThrow()
    }
}