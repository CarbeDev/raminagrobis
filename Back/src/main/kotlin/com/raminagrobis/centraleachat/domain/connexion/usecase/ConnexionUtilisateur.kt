package com.raminagrobis.centraleachat.domain.connexion.usecase

import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class ConnexionUtilisateur(
    private val repo : IUtilisateurRepo,
    private val jwt : IJWTTokenUtil) {

    fun handle(email : String, mdp : String,admin: Boolean) : String{
        val utilisateur = getUtilisateur(email,admin)

        if (BCrypt.checkpw(mdp,utilisateur.motDePasse)){
            return jwt.generateToken(utilisateur.toSpringUser())
        } else {
            throw BadPasswordException()
        }
    }

    fun getUtilisateur(email: String,admin: Boolean) : Utilisateur{
        if (admin) return repo.findAdminByEmail(email) else return repo.findSocieteByEmail(email)
    }
}