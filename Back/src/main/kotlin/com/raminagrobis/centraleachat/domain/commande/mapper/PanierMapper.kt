package com.raminagrobis.centraleachat.domain.commande.mapper

import com.raminagrobis.centraleachat.domain.commande.dto.PanierConfirme
import com.raminagrobis.centraleachat.domain.commande.dto.PanierDTO
import com.raminagrobis.centraleachat.infra.panier.entity.PanierEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PanierMapper {

    fun toDTO(entity : PanierEntity) : PanierDTO
    fun toEntity(dto : PanierDTO) : PanierEntity
    fun toEntity(panierConfirme: PanierConfirme) : PanierEntity
}