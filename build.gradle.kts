plugins {
    id("com.android.application").version("7.2.2").apply(false)
    id("com.android.library").version("7.2.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.0").apply(false)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
    id("com.apollographql.apollo3").version("3.7.4").apply(false)
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}