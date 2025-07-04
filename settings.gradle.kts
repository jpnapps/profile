pluginManagement {
    repositories {
        google()
       /* google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroup("com.google.dagger")
            }
        }*/
        mavenCentral()
        gradlePluginPortal()
        resolutionStrategy {
            eachPlugin {
                if (requested.id.id == "dagger.hilt.android.plugin") {
                    useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
                }
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Jithish"
include(":app")
include(":data:ui")
include(":data:remote")
include(":data:local")

include(":feature:favorites")
include(":feature:notes")
include(":feature:profile")
include(":feature:pwd")
include(":feature:more")

include(":domain")

include(":core:utils")
include(":core:ui")

project(":data:ui").projectDir = file("data/ui")
project(":data:remote").projectDir = file("data/remote")
project(":data:local").projectDir = file("data/local")

project(":feature:favorites").projectDir = file("feature/favorites")
project(":feature:notes").projectDir = file("feature/notes")
project(":feature:profile").projectDir = file("feature/profile")
project(":feature:pwd").projectDir = file("feature/pwd")
project(":feature:more").projectDir = file("feature/more")

project(":core:utils").projectDir = file("core/utils")
project(":core:ui").projectDir = file("core/ui")
