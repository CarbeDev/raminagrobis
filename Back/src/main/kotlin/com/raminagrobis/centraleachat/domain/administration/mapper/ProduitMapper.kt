package com.raminagrobis.centraleachat.domain.administration.mapper

import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDTO
import com.raminagrobis.centraleachat.domain.administration.dto.ProduitDetail
import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ProduitMapper {

    fun toDTO(entity: ProduitEntity) : ProduitDetail
    fun toEntity(detail: ProduitDetail) : ProduitEntity
    @Mapping(target = "categorie.id", source = "idCategorie")
    fun toEntity(dto : ProduitDTO) : ProduitEntity
}