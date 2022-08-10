pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
        versionCatalogs {
            create("libs") {
                from(files("$rootDir/gradle/libs.version.toml"))
            }
    }
}
rootProject.name = "devsprint-gustavo-santorio-3"
include(":app")
include(":buildSrc")
include(":buildSrc")
