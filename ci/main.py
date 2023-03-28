import sys
import anyio

import dagger

async def main():

    config = dagger.Config(log_output=sys.stdout)

    async with dagger.Connection(config) as client:
        container = (
            client.container()
            .from_("openjdk:17-alpine")
            .with_mounted_directory("project",client.host().directory("./Back", exclude = ["ci/"]))
        )

        test =  container.with_workdir("/project").with_exec(["./gradlew", "check"])
        
        build = test.with_exec(["./gradlew", "build"]).directory("./build")

        await build.export("./build")
        e = await build.entries()

        print(f"build dir contents:\n{e}")


        print(out)


anyio.run(main)
