package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.model.Societe
import org.springframework.stereotype.Service

@Service
class GetSocietes(private val userRepo: ISocieteRepo) {

    fun handle(): Iterable<Societe> {
        return userRepo.getAll()
    }
}