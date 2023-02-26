package com.raminagrobis.centraleachat.domain.user.model

class User (var email : String, var prenom : String, var nom : String, val role: Role, var actif : Boolean){

    private var id : Int? = null
    constructor(id : Int, email: String, prenom: String, nom: String, role: Role, actif: Boolean) : this(email, prenom, nom, role, actif){
        this.id = id
    }
}