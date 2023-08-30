<script lang="ts">
    import 'bulma/css/bulma.css'
    import axios from 'axios'
    import {ConnexionApi, ConnexionData} from "../api/ConnexionApi";
    import type {GetTokenResponse} from "../api/ConnexionApi";

    let erreurConnexion = false

    async function handleSubmit(e: SubmitEvent) {
        e.preventDefault()
        const data: ConnexionData = {
            "email": document.getElementById("email").value,
            "mdp": document.getElementById("mdp").value,
            "admin": false
        }

        new ConnexionApi().getToken(data).then((token) => {
            createCookie(token)
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