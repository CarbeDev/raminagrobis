package com.raminagrobis.centraleachat.infra.redis.session

import com.raminagrobis.centraleachat.domain.connexion.dto.Session
import com.raminagrobis.centraleachat.domain.connexion.mapper.SessionMapper
import com.raminagrobis.centraleachat.domain.connexion.port.SessionPort
import org.springframework.stereotype.Repository

@Repository
class SessionAdapter(
    val repo : SessionRepo,
    val mapper : SessionMapper
) : SessionPort {
    override fun creerSession(session: Session) {
        repo.save(mapper.toEntity(session))
    }

    override fun getSession(jwt: String): Session {
        return mapper.toDto(repo.findById(jwt).orElseThrow())
    }

    override fun supprimerSession(jwt: String) {
        repo.deleteById(jwt)
    }
}