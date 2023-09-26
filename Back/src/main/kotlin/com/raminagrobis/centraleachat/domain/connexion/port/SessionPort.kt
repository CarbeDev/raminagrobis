package com.raminagrobis.centraleachat.domain.connexion.port

import com.raminagrobis.centraleachat.domain.connexion.dto.Session

interface SessionPort {
    fun creerSession(session: Session)
    fun getSession(jwt : String) : Session?
    fun supprimerSession(jwt : String)
}