package com.raminagrobis.centraleachat.domain.demande.mapper

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.infra.demande.entity.DemandeEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DemandeMapper {

    fun toDTO(entity : DemandeEntity) : DemandeDTO
    fun toEntity(dto : DemandeDTO) : DemandeEntity
}