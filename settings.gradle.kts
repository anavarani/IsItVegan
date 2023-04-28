pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Is it vegan"
include(":app")
include(":feature:home")
include(":core:data")
include(":core:model")
include(":core:database")
include(":core:common")
include(":core:network")
include(":core:domain")
include(":feature:scanner")
include(":feature:history")
include(":feature:productdetail")
