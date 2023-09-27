package com.raminagrobis.centraleachat.app.controller.connexion

import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenUtil
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.usecase.ConnexionUtilisateur
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun connexionSociete(@RequestBody connexionFormData : ConnexionForm, request : HttpServletRequest): Map<String,String>{

        val token = connexionUtilisateur.handle(connexionFormData.email,connexionFormData.mdp,request.remoteAddr,connexionFormData.admin)
        return mapOf(
            "Token" to token,
            "Issued at" to jwtTokenUtil.getIssuedAt(token),
            "Expire" to jwtTokenUtil.getExpiration(token),
        )
    }

    @GetMapping("/token/role/{token}")
    fun getRoleFromToken(@PathVariable token : String) : Role? {
        return jwtTokenUtil.getUtilisateurFromToken(token).role
    }

    data class ConnexionForm(
        val email :String,
        val mdp : String,
        val admin : Boolean
    )
}