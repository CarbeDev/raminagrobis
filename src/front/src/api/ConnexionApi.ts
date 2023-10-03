import {Api} from "./Api";

export class ConnexionApi extends Api{

    async getToken(data : ConnexionData) : Promise<GetTokenResponse>{
        try {
            const response = await super.post("/connexion", data)
            return {
                token : response.data.Token,
                expire : response.data.Expire
            }
        }
        catch (error){
            throw error
        }
    }

    async getRole(token : string) {
        const response = await super.get("/token/role/"+ token)
        return response.data
    }


    // @ts-ignore
    havePermission(path : string) : boolean{
        console.log(path)
        this.getRole(this.getTokenCookie()).then((role)=>{
            console.log(path)
            switch (role){
                case "ADMIN":
                    console.log("2")
                    return !path.startsWith("/admin/")
                case "FOURNISSEUR":
                    console.log("3")
                    return !path.startsWith("/fournisseur")
                case "ADHERENT":
                    console.log("4")
                    return !path.startsWith("/adherent")
                default:
                    console.log("4")
                    return true
            }
        }).catch(error => {
            return true
        })
     }
    // @ts-ignore
    getTokenCookie() : string{
        try {
            return document.cookie
                .split("; ")
                .find( (row)=> row.startsWith("token"))!!
                .split("=")[1]
        } catch (e){
            console.log(e)
            throw e
        }
    }


}

export interface GetTokenResponse {
    token : string,
    expire : string
}

export interface ConnexionData{
    email : String,
    motDePasse : String,
    isAdmin : Boolean
}