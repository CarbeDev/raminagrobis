package com.raminagrobis.centraleachat.domain.user.model

class User (var email : String, var prenom : String, var nom : String, val role: Role){

    private var id : Int? = null
    constructor(id : Int, email: String, prenom: String, nom: String, role: Role) : this(email, prenom, nom, role){
        this.id = id
    }
}