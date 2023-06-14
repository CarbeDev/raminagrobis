package com.raminagrobis.centraleachat.domain.connexion.mapper

import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.infra.utilisateur.entity.AdminEntity
import com.raminagrobis.centraleachat.infra.utilisateur.entity.SocieteEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UtilisateurMapper {

    fun toUtilisateur(entity : SocieteEntity) : Utilisateur
    fun toUtilisateur(entity : AdminEntity) : Utilisateur
}