package com.raminagrobis.centraleachat.app.controller.connexion

import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenUtil
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Connexion")
@RestController
class ConnexionController(
    val connexionUtilisateur: ConnexionUtilisateur,
    val jwtTokenUtil: JWTTokenUtil
){

    @PostMapping("/connexion")
    fun connexionSociete(@RequestBody connexionFormData : ConnexionForm): Map<String,String>{

        val token = connexionUtilisateur.handle(connexionFormData.email,connexionFormData.mdp,connexionFormData.admin)

        return mapOf(
            Pair("Token", token),
            Pair("Issued at", jwtTokenUtil.getIssuedAt(token)),
            Pair("Expire", jwtTokenUtil.getExpiration(token))
        )
    }

    data class ConnexionForm(
        val email :String,
        val mdp : String,
        val admin : Boolean
    )
}