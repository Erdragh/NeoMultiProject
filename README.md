# NeoForge Multiproject Example

This is a repository containing an example multiproject Gradle setup for
a `lib` mod and a `consumer` mod that depends on and includes the `lib` mod.

Data generation is also preconfigured. The Gradle Plugin in use is NeoForge's ModDevGradle.

Dependency versions are defined via Gradle's Version Catalog system, in [gradle/libs.versions.toml](gradle/libs.versions.toml).