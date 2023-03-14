package com.raminagrobis.centraleachat.domain.societe.model

class Societe (var email : String, var nom : String, val role: Role, var actif : Boolean){

    private var id : Int? = null
    constructor(id : Int, email: String, nom: String, role: Role, actif: Boolean) : this(email, nom, role, actif){
        this.id = id
    }
}