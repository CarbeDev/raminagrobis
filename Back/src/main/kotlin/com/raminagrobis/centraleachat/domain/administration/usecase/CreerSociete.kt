package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.PasswordGenerator
import org.springframework.stereotype.Service


@Service
class CreerSociete(private val societeRepo : ISocieteRepo) {

    fun handle(email: String, nom: String, role: Role) {
        val actif = true
        if (societeRepo.isEmailUnique(email)) societeRepo.saveSociete(UserSociete(
            nom = nom,
            email = email,
            motDePasse = genererMotDePasse(),
            actif = actif,
            role = role
        )) else throw EmailAlreadyUseException()

    }

    fun genererMotDePasse() : String{
        val gen = PasswordGenerator()

        val rules = listOf(
            CharacterRule(EnglishCharacterData.LowerCase),
            CharacterRule(EnglishCharacterData.UpperCase),
            CharacterRule(EnglishCharacterData.Digit),
            CharacterRule(EnglishCharacterData.Special)
        )

        return gen.generatePassword(12, rules)
    }
}
