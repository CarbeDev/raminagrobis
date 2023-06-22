package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.DetailSociete
import org.springframework.stereotype.Service

@Service
class RecupererSociete(private val repo : ISocieteRepo){

    fun handle(id : Int) : DetailSociete{
        return repo.findSocieteByID(id)
    }
}