package com.raminagrobis.centraleachat.domain.connexion.adapter

import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur

interface IUtilisateurRepo {
    fun findSocieteByEmail(email : String) : Utilisateur
    fun findAdminByEmail(email: String): Utilisateur
}