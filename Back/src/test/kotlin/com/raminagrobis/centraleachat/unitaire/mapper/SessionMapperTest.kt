package com.raminagrobis.centraleachat.unitaire.mapper

import com.raminagrobis.centraleachat.domain.connexion.dto.Session
import com.raminagrobis.centraleachat.domain.connexion.mapper.SessionMapperImpl
import com.raminagrobis.centraleachat.infra.redis.session.SessionEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SessionMapperTest {

    @Test
    fun entityToDTO(){
        val session = SessionEntity("token","192.0.0.1")

        val dto = SessionMapperImpl().toDto(session)

        assertEquals(session.jwt,dto.jwt)
        assertEquals(session.ip,session.ip)
    }

    @Test
    fun dtoToEntity(){
        val session = Session("token", "192.0.0.1")

        val entity = SessionMapperImpl().toEntity(session)

        assertEquals(session.jwt,entity.jwt)
        assertEquals(session.ip,entity.ip)
    }
}