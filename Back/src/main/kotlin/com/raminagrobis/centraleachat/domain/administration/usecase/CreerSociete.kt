package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.UserSociete
import com.raminagrobis.centraleachat.domain.administration.exception.EmailAlreadyUseException
import com.raminagrobis.centraleachat.domain.administration.model.Role
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.PasswordGenerator
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Pattern


@Service
class CreerSociete(private val societeRepo : ISocieteRepo){

    @Throws(EmailAlreadyUseException::class)
    fun handle(email: String, nom: String, role: Role) : UserSociete{
        if (email.isEmail()){
            val societe = UserSociete(
                nom = nom,
                email = email,
                motDePasse = genererMotDePasse(),
                actif = true,
                dateInscription = Date(),
                role = role
            )

            if (societeRepo.isEmailUnique(email)) {
                societeRepo.saveSociete(societe)
                return societe
            } else throw EmailAlreadyUseException()
        }else{
            throw IllegalArgumentException()
        }
    }
}

private fun genererMotDePasse() : String{
    val gen = PasswordGenerator()

    val rules = listOf(
        CharacterRule(EnglishCharacterData.LowerCase),
        CharacterRule(EnglishCharacterData.UpperCase),
        CharacterRule(EnglishCharacterData.Digit),
        CharacterRule(EnglishCharacterData.Special)
    )

    return gen.generatePassword(12, rules)
}
private fun String.isEmail() : Boolean{
    val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    return pattern.matcher(this).matches()
}
