package com.raminagrobis.centraleachat.domain.administration.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SocieteMapper {

    fun toDTO(entity: SocieteEntity) : SocieteDTO
    fun toDetail(entity: SocieteEntity) : DetailSociete


    fun toEntity(dto: SocieteDTO) : SocieteEntity
    fun toEntity(dto: DetailSociete) : SocieteEntity
    fun toEntity(dto : UserSociete) : SocieteEntity
}