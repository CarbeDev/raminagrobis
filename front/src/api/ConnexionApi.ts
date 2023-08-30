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