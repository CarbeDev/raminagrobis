<script lang="ts">
    import 'bulma/css/bulma.css'
    import {ConnexionApi} from "../api/ConnexionApi";
    import type {GetTokenResponse, ConnexionData} from "../api/ConnexionApi";

    let erreurConnexion = false

    async function handleSubmit(e: SubmitEvent) {
        e.preventDefault()

        const api = new ConnexionApi()

        const data: ConnexionData = {
            "email": document.getElementById("email").value,
            "mdp": document.getElementById("mdp").value,
            "admin": false
        }

        api.getToken(data).then((tokenResponse) => {
            createCookie(tokenResponse)
            api.getRole(tokenResponse.token).then((role)=>{
                redirection(role)
            })
        }).catch((error) => {
            console.log(error)
            erreurConnexion = true
        })
    }

    function createCookie(response : GetTokenResponse) {
        document.cookie = `token=${response.token}; expires=${response.expire}; SameSite=None; Secure;`
    }

    function closeNotification(){
        erreurConnexion = false
    }

    function redirection(role : String){
        switch (role){
            case "ADHERENT" :
                document.location.href = "/adherent"
                break
            case "FOURNISSEUR" :
                document.location.href="/fournisseur"
                break
            default:
                console.log(role)
                erreurConnexion = true
        }
    }
</script>

{#if erreurConnexion}
    <div class="notification is-danger">
        <button class="delete" on:click={closeNotification}/>
        Les informations d'identification que vous avez fournies sont incorrectes. Veuillez vérifier votre nom d'utilisateur et votre mot de passe, puis réessayez.
    </div>
{/if}
<form on:submit={handleSubmit} class="is-flex is-flex-direction-column">
    <div class="field">
        <label class="label">
            Email
        </label>
        <div class="control">
            <input class="input" type="email" id="email" name="email" placeholder="example@xyz.com" required/>
        </div>
    </div>

    <div class="field">
        <label class="label">
            Mot de passe
        </label>

        <div class="control">
            <input class="input" type="password" id="mdp"/>
        </div>
    </div>

    <div class="field is-align-self-flex-end">
        <div class="control">
            <button class="button is-primary" type="submit">Valider</button>
        </div>
    </div>
</form>