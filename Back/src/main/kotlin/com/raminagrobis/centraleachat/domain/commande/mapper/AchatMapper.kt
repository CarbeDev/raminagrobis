package com.raminagrobis.centraleachat.domain.commande.mapper

import com.raminagrobis.centraleachat.domain.commande.dto.AchatConfirme
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface AchatMapper {

    fun toDTO(entity : AchatEntity) : AchatDTO


    @Mapping(target = "key.idSociete", source = "adherent.id")
    @Mapping(target = "key.reference", source = "produit.reference")
    @Mapping(target = "key.idpanier", source = "panier.id")
    fun toEntity(dto : AchatDTO) : AchatEntity

    @Mapping(target = "key.idSociete", source = "adherent.id")
    @Mapping(target = "key.reference", source = "produit.reference")
    @Mapping(target = "key.idpanier", source = "panier.id")
    fun toEntity(achatConfirme: AchatConfirme) : AchatEntity
}