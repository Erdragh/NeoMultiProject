plugins {
    java
}

val modId: String by project
val modGroup: String by project
val modName: String by project
val modAuthors: String by project
val modDescription: String by project
val modVersion: String by project

sourceSets {
    register("datagen") {
        compileClasspath += main.get().output + main.get().compileClasspath
        runtimeClasspath += main.get().output + main.get().runtimeClasspath
    }
}

neoForge {
    version = libs.versions.neoforge

    parchment.mappingsVersion = libs.versions.parchment
    parchment.minecraftVersion = libs.versions.parchmentMc

    val datagenModId = "$modId-datagen"
    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
        register(datagenModId) {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets.named("datagen").get())
        }
    }

    runs {
        configureEach {
            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        register("client") {
            client()
            gameDirectory = project.file("runs/client")
        }

        register("server") {
            server()
            gameDirectory = project.file("runs/server")
            programArgument("--nogui")
        }

        register("data") {
            data()
            gameDirectory = project.file("runs/data")
            sourceSet = sourceSets.named("datagen").get()
            loadedMods = setOf(neoForge.mods.named(datagenModId).get())

            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            programArguments.addAll("--mod", modId, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
        }
    }

    ideSyncTask(tasks.generateModMetadata)
}

dependencies {
    implementation(projects.lib)
    jarJar(projects.lib)
}