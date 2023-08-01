package com.raminagrobis.centraleachat.domain.demande.mapper

import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDTO
import com.raminagrobis.centraleachat.domain.demande.dto.DemandeDetail
import com.raminagrobis.centraleachat.infra.demande.entity.DemandeEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface DemandeMapper {

    fun toDTO(entity : DemandeEntity) : DemandeDetail
    fun toEntity(detail : DemandeDetail) : DemandeEntity

    @Mapping(target = "societe.id", source = "idSociete")
    @Mapping(target = "categorie.id", source = "idCategorie")
    fun toEntity(demande : DemandeDTO) : DemandeEntity
}