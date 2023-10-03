package com.raminagrobis.centraleachat.domain.connexion.mapper

import com.raminagrobis.centraleachat.domain.connexion.dto.Session
import com.raminagrobis.centraleachat.infra.redis.session.SessionEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SessionMapper {

    fun toDto(sessionEntity: SessionEntity) : Session

    fun toEntity(session: Session) : SessionEntity
}