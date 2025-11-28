rootProject.name = "GamepediaApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
include(":modules:coredatabase")
include(":modules:corenetwork")

include(":modules:common:data")
include(":modules:common:domain")
include(":modules:common:ui")

include(":modules:search:data")
include(":modules:search:domain")
include(":modules:search:ui")

include(":modules:favorite:data")
include(":modules:favorite:domain")
include(":modules:favorite:ui")

include(":modules:game:data")
include(":modules:game:domain")
include(":modules:game:ui")