package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import com.raminagrobis.centraleachat.domain.commande.adapter.IAchatRepo
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.PasswordGenerator
import org.springframework.stereotype.Service

@Service
class SupprimerSociete(private val societeRepo : ISocieteRepo, private val achatRepo : IAchatRepo){

    fun handle(id : Int){

        val societe = societeRepo.findSocieteByID(id)

        if(achatRepo.getNbAchatBySociete(societe) >= 1) {
            desactiveSociete(societe)
        } else {
            societeRepo.deleteSociete(id)
        }
    }

    private fun desactiveSociete(societe: DetailSociete) {
        societe.actif = false
        societe.anonymisation()
        societeRepo.saveSociete(societe)
    }

    private fun DetailSociete.anonymisation(){
        val gen = PasswordGenerator()

        val rules = listOf(
            CharacterRule(EnglishCharacterData.LowerCase),
            CharacterRule(EnglishCharacterData.Digit)
        )

        this.nom =gen.generatePassword(22,rules)
        this.email = "${this.nom}@email.fr"
    }
}