package com.raminagrobis.centraleachat.domain.administration.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProduitMapper {

    fun toDTO(entity: ProduitEntity) : ProduitDTO
    fun toEntity(dto: ProduitDTO) : ProduitEntity
}