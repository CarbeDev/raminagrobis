<script lang="ts">
    import 'bulma/css/bulma.css'
    import type ConnexionData from "../models/ConnexionData";
    import axios from 'axios'
    import ErrorBox from "./Box/ErrorBox.svelte";

    let erreurConnexion = false

    async function handleSubmit(e: SubmitEvent) {
        e.preventDefault()
        const data: ConnexionData = {
            "email": document.getElementById("email").value,
            "mdp": document.getElementById("mdp").value,
            "admin": false
        }

        await axios.post("http://localhost:8080/connexion", data).then((response) => {
            createCookie(response)
        }).catch((error) => {
            erreurConnexion = true
        })
    }

    function createCookie(response) {
        document.cookie = `token=${response.data.Token}; expires=${response.data.Expire}; SameSite=None; Secure;`
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