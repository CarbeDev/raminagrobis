export default class AuthUtil{

    static loginRedirection(role : String){
        switch (role){
            case "ADHERENT" :
                document.location.href = "/adherent"
                break
            case "FOURNISSEUR" :
                document.location.href="/fournisseur"
                break
            case "ADMIN" :
                document.location.href="/admin/dashboard"
                break
            default:
                console.log(role)
        }
    }
}