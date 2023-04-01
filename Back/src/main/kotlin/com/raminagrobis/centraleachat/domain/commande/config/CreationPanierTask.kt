package com.raminagrobis.centraleachat.domain.commande.config

import com.raminagrobis.centraleachat.domain.commande.adapter.IPanierRepo
import com.raminagrobis.centraleachat.domain.commande.model.Etat
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class CreationPanierTask(val repo : IPanierRepo) {

    @Scheduled(cron = "0 0 1 * * MON")
    fun handle(){
        fermerAncienPanier()
    }

    fun fermerAncienPanier(){
        repo.getPaniersOuvert().forEach{
            it.etat = Etat.FERMER
            repo.savepanier(it)
        }
    }
}