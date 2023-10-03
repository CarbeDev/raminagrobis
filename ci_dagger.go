package main

import (
	"context"
	"fmt"
	"os"

	"dagger.io/dagger"
)

func main() {
	err := build(context.Background())

	if err != nil {
		fmt.Println(err)
	}
}

func build(ctx context.Context) error {
	fmt.Println("Initialisation de Dagger")

	client, err := dagger.Connect(ctx, dagger.WithLogOutput(os.Stderr))
	if err != nil {
		return err
	}

	defer client.Close()

	fmt.Println("Creation du conteneur")
	container := client.Container().From("openjdk:17-alpine").
		WithDirectory("/src", client.Host().Directory("./src/Back")).WithWorkdir("/src")

	fmt.Println("Exécution des test")
	out, err := container.
		WithExec([]string{"./gradlew", "testCI"}).
		//WithExec([]string{"./gradlew", "build"}).
		Stdout(ctx)

	fmt.Println(out)
	if err != nil {
		return err
	}

	return nil
}
