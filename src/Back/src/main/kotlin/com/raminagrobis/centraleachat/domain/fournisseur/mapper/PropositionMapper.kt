package com.raminagrobis.centraleachat.domain.fournisseur.mapper

import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDTO
import com.raminagrobis.centraleachat.domain.fournisseur.dto.PropositionDetail
import com.raminagrobis.centraleachat.infra.proposition.entity.PropositionEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PropositionMapper {

    fun toDTO(entity : PropositionEntity) : PropositionDetail
    @Mapping(target = "propositionKey.reference", source = "refProduit")
    @Mapping(target = "propositionKey.idSociete", source = "idSociete")
    fun toEntity(dto : PropositionDTO) : PropositionEntity
}