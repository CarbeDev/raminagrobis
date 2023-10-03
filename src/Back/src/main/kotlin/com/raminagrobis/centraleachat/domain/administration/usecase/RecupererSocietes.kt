package com.raminagrobis.centraleachat.domain.administration.usecase

import com.raminagrobis.centraleachat.domain.administration.adapter.ISocieteRepo
import com.raminagrobis.centraleachat.domain.administration.dto.SocieteDTO
import org.springframework.stereotype.Service

@Service
class RecupererSocietes(private val repo: ISocieteRepo) {

    fun handle(): Iterable<SocieteDTO> {
        return repo.getAll()
    }



}