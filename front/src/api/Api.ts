import axios from "axios";

export class Api{

    readonly suffix : string = "http://localhost:8080"

    async post(endpoint : string, data : Object){
        return await axios.post(this.suffix.concat(endpoint), data)
    }
}