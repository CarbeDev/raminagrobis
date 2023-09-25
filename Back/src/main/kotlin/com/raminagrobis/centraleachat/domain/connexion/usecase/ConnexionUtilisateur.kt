package com.raminagrobis.centraleachat.domain.connexion.usecase

import com.raminagrobis.centraleachat.domain.connexion.adapter.IJWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.adapter.IUtilisateurRepo
import com.raminagrobis.centraleachat.domain.connexion.dto.Session
import com.raminagrobis.centraleachat.domain.connexion.exception.BadPasswordException
import com.raminagrobis.centraleachat.domain.connexion.exception.UserNotFoundException
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.domain.connexion.port.SessionPort
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class ConnexionUtilisateur(
    private val utilisateurRepo : IUtilisateurRepo,
    private val jwt : IJWTTokenUtil,
    private val sessionRepo : SessionPort
    ) {

    @Throws(UserNotFoundException::class, BadPasswordException::class)
    fun handle(email : String, mdp : String, ip : String, admin: Boolean) : String{
        val utilisateur = getUtilisateur(email,admin)

        if (utilisateur != null) {
            if (BCrypt.checkpw(mdp, utilisateur.motDePasse)) {
                val token = jwt.generateToken(utilisateur.toSpringUser())
                creationSession(token, ip)
                return token
            } else {
                throw BadPasswordException()
            }
        } else{
            throw UserNotFoundException("Aucun utilisateur avec cet email n'a été trouvé : $email")
        }
    }

    fun getUtilisateur(email: String,admin: Boolean) : Utilisateur{
        if (admin) return utilisateurRepo.findAdminByEmail(email) else return utilisateurRepo.findSocieteByEmail(email)
    }

    fun creationSession(jwt : String,ip : String){
        sessionRepo.creerSession(Session(jwt,ip))
    }
}