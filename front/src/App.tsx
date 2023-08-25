import React from 'react';
import './App.css';
import '@radix-ui/themes/styles.css';
import {Theme} from "@radix-ui/themes";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import axios from "axios";

const router = createBrowserRouter([
    {
        path : "/",
        element : <Connexion/>
    }
])
export default function App() {
    return (
        <html>
            <body>
                <Theme>
                    <RouterProvider router={router} />
                </Theme>
            </body>
        </html>
    );

}

 function Connexion(){
    function handleSubmit(event : React.FormEvent<HTMLFormElement>){
        event.preventDefault()

        const form = new FormData(event.currentTarget)
        const data : FormData = {
            email : form.get("email") as string,
            mdp : form.get("mdp") as string,
            admin : false
        }

        axios.post("http://localhost:8080/connexion", data).then((response)=>
            console.log(response)
        ).catch((error) => {
            console.log(error.response)
        })
    }
    return(
        <form onSubmit={handleSubmit}>
            <input
                type="email"
                name="email"
                placeholder="example@xyz.fr"
            />

            <input
                type="password"
                name="mdp"
            />

            <button type="submit"> Valider </button>
        </form>
    )
 }

 interface FormData {
    email : String;
    mdp : String;
    admin : Boolean
 }
