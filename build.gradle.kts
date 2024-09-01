import org.jetbrains.kotlin.gradle.utils.extendsFrom

plugins {
    java
    idea
    kotlin("jvm") version "2.0.20" apply false
    alias(libs.plugins.moddev) apply false
}

subprojects {
    apply(plugin = "base")
    apply(plugin = "net.neoforged.moddev")
    val modId: String by project
    val modGroup: String by project
    val modName: String by project
    val modAuthors: String by project
    val modDescription: String by project
    val modVersion: String by project
    val modLicense: String by project

    base.archivesName = modId
    group = modGroup
    version = "${modVersion}-mc${rootProject.libs.versions.minecraft.get()}"

    // This block of code expands all declared replace properties in the specified resource targets.
    // A missing property will result in an error. Properties are expanded using ${} Groovy notation.
    val generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
        val replaceProperties = mapOf(
            "minecraft_version"       to libs.versions.minecraft,
            "minecraft_version_range" to libs.versions.minecraftRange,
            "neo_version"             to libs.versions.neoforge,
            "neo_version_range"       to libs.versions.neoforgeRange,
            "loader_version_range"    to libs.versions.loaderRange,
            "mod_id"                  to modId,
            "mod_name"                to modName,
            "mod_license"             to modLicense,
            "mod_version"             to version,
            "mod_authors"             to modAuthors,
            "mod_description"         to modDescription
        )
        inputs.properties(replaceProperties)
        expand(replaceProperties)
        from("src/main/templates")
        into("build/generated/sources/modMetadata")
    }

    sourceSets {
        main {
            resources {
                // Include resources generated by data generators.
                srcDir("src/generated/resources")
                exclude(".cache")
                // Include the output of "generateModMetadata" as an input directory for the build
                // this works with both building through Gradle and the IDE.
                srcDir(generateModMetadata)
            }
        }
    }
}

tasks.named<Wrapper>("wrapper") {
    // Define wrapper values here so as to not have to always do so when updating gradlew.properties.
    // Switching this to Wrapper.DistributionType.ALL will download the full gradle sources that comes with
    // documentation attached on cursor hover of gradle classes and methods. However, this comes with increased
    // file size for Gradle. If you do switch this to ALL, run the Gradle wrapper task twice afterwards.
    // (Verify by checking gradle/wrapper/gradle-wrapper.properties to see if distributionUrl now points to `-all`)
    distributionType = Wrapper.DistributionType.BIN
}

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}