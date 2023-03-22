package com.raminagrobis.centraleachat.domain.societe.usecase

import com.raminagrobis.centraleachat.domain.societe.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.societe.model.Societe
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class GetSocietes(@Lazy val userRepo: ISocieteRepo) {

    fun handle(): Iterable<Societe> {
        return userRepo.getAll()
    }
}