dependencyResolutionManagement {
  versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
  pluginManagement {
    repositories {
      mavenCentral()
      gradlePluginPortal()
      google()
      maven("https://oss.sonatype.org/content/repositories/snapshots")
      maven("https://repo.spring.io/milestone")
    }
  }
}

rootProject.name = "fcwfp-root"

include("imperative-vs-declarative")

include("imperative-vs-declarative-kt")

include("railway-oriented-validation")

include("railway-oriented-validation-kt")
