package com.raminagrobis.centraleachat.domain.connexion.usecase

import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class ConnexionUtilisateur(
    private val repo : IUtilisateurRepo,
    private val jwt : IJWTTokenUtil) {

    fun handle(email : String, mdp : String) : String{
        val utilisateur = repo.findSocieteByEmail(email) ?: throw UsernameNotFoundException(email)

        if (BCrypt.checkpw(mdp,utilisateur.motDePasse)){
            return jwt.generateToken(User(utilisateur.email, utilisateur.motDePasse, utilisateur.getAuthority()))
        } else {
            throw BadPasswordException()
        }
    }
}