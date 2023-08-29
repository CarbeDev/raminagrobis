import React from 'react';
import './App.css';
import '@radix-ui/themes/styles.css';
import {Button, Card, Flex, TextFieldInput, Theme} from "@radix-ui/themes";
import * as Form from '@radix-ui/react-form';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import axios from "axios";
import {FormControl, FormField, FormLabel, FormMessage, FormSubmit} from "@radix-ui/react-form";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Connexion/>
    }
])
export default function App() {
    return (
        <Theme >
            <RouterProvider router={router}/>
        </Theme>
    );

}

function Connexion() {
    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault()

        const form = new FormData(event.currentTarget)
        const data: FormData = {
            email: form.get("email") as string,
            mdp: form.get("mdp") as string,
            admin: false
        }

        console.log(data)
        // axios.post("http://localhost:8080/connexion", data).then((response) =>
        //     console.log(response)
        // ).catch((error) => {
        //     console.log(error.response)
        // })
    }

    return (
        <Flex justify="center" bottom={"50%"} height="100%">
            <Card>
                <Form.Root onSubmit={handleSubmit}>
                    <FormField name="email">
                        <Flex align="baseline" justify="between">
                            <FormLabel>Email</FormLabel>
                            <FormMessage match="valueMissing">Veuillez entrer un email</FormMessage>
                            <FormMessage match="typeMismatch">Veuillez entre un email au bon format</FormMessage>
                        </Flex>
                        <FormControl asChild>
                            <TextFieldInput
                                type="email"
                                name="email"
                                placeholder="example@xyz.fr"
                                required
                            />
                        </FormControl>
                    </FormField>

                    <FormField name="mdp">
                        <Flex align="baseline" justify="between">
                            <FormLabel>Mot de passe</FormLabel>
                            <FormMessage match="tooShort">Le mot de passe doit au moins faire 12 caractères</FormMessage>
                        </Flex>
                        <FormControl asChild>
                            <TextFieldInput
                                type="password"
                                name="mdp"
                                minLength={12}
                                required
                            />
                        </FormControl>
                    </FormField>

                    <FormSubmit asChild>
                        <Button> Valider</Button>
                    </FormSubmit>
                </Form.Root>
            </Card>
        </Flex>

    )
}

interface FormData {
    email: String;
    mdp: String;
    admin: Boolean
}
