package com.raminagrobis.centraleachat.domain.connexion.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class BadPasswordException : Exception()