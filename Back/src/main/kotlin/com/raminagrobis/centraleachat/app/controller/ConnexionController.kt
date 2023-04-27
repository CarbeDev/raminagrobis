package com.raminagrobis.centraleachat.app.controller

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
    fun connexionSociete(@RequestBody formData : Form): Map<String,String>{
        val r = HashMap<String,String>()

        val token = connexionUtilisateur.handle(formData.email,formData.mdp)

        r["Token"] = token
        r["Issued at"] = jwtTokenUtil.getIssuedAt(token)
        r["Expire"] = jwtTokenUtil.getExpiration(token)

        return r
    }

    data class Form(
        val email :String,
        val mdp : String
    )
}