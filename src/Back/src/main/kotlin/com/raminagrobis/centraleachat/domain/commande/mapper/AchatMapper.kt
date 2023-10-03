package com.raminagrobis.centraleachat.domain.commande.mapper

import com.raminagrobis.centraleachat.domain.commande.dto.AchatDetail
import com.raminagrobis.centraleachat.domain.commande.dto.AchatDTO
import com.raminagrobis.centraleachat.domain.commande.dto.AchatMin
import com.raminagrobis.centraleachat.infra.achat.entity.AchatEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface AchatMapper {

    fun toDetail(entity : AchatEntity) : AchatDetail


    @Mapping(target = "key.idSociete", source = "adherent.id")
    @Mapping(target = "key.reference", source = "produit.reference")
    @Mapping(target = "key.idPanier", source = "panier.id")
    fun toEntity(dto : AchatDTO) : AchatEntity

    @Mapping(target = "key.idSociete", source = "adherent.id")
    @Mapping(target = "key.reference", source = "produit.reference")
    @Mapping(target = "key.idPanier", source = "panier.id")
    fun toEntity(achatDetail: AchatDetail) : AchatEntity

    @Mapping(target = "key.idSociete", source = "idAdherent")
    @Mapping(target = "key.reference", source = "refProduit")
    @Mapping(target = "key.idPanier", source = "idPanier")
    fun toEntity(achat: AchatMin): AchatEntity
}