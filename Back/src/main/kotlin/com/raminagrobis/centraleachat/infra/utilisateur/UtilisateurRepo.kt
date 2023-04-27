package com.raminagrobis.centraleachat.infra.utilisateur

import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.infra.SocieteRepo
import org.springframework.stereotype.Repository

@Repository
class UtilisateurRepo(var societeRepo: SocieteRepo, var adminRepo : AdminRepo) : IUtilisateurRepo{
    override fun findSocieteByEmail(email : String): Utilisateur {
        return societeRepo.getByEmail(email)
    }

    override fun findAdminByEmail(email: String): Utilisateur {
        return adminRepo.getByEmail(email)
    }
}