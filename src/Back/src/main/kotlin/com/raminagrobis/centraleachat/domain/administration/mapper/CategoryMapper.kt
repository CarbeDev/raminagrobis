package com.raminagrobis.centraleachat.domain.administration.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.CategorieDTO
import com.raminagrobis.centraleachat.infra.produit.entity.CategorieEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "string")
interface CategoryMapper {

    fun toDTO(categorieEntity: CategorieEntity): CategorieDTO
    fun toEntity(categorieDTO: CategorieDTO) : CategorieEntity
}