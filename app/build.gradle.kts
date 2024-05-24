import pl.mankevich.githubrepositorybrowserum.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.apollographql.apollo3")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationPackage
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.androidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = Configs.freeCompilerArgs
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeKotlinCompilerExt
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
    }

    apollo {
        service("service") {
            packageName.set(Configs.applicationPackage)
        }
    }
}

dependencies {

    //Kotlin
    implementation(Libs.Kotlin.coreKtx)
    implementation(Libs.Kotlin.coroutinesCore)
    implementation(Libs.Kotlin.coroutinesAndroid)

    //Lifecycle
    implementation(Libs.Lifecycle.lifecycleRuntime)
    implementation(Libs.Lifecycle.lifecycleViewModel)
    implementation(Libs.Lifecycle.lifecycleRuntimeCompose)
    implementation(Libs.Lifecycle.lifecycleViewModelCompose)

    //DI
    implementation(Libs.DI.hiltAndroid)
    kapt(Libs.DI.hiltCompiler)
    implementation(Libs.DI.czerwinskiHiltExt)
    kapt(Libs.DI.czerwinskiHiltProcessor)

    //Network
    implementation(Libs.Network.apolloKotlin)

    //Compose
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.preview)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.navigation)
    implementation(Libs.Compose.hiltNavigation)
    implementation(Libs.Compose.activity)
    implementation(Libs.Compose.paging)

    //Paging
    implementation(Libs.Paging.pagingRuntime)

    //Debug
    debugImplementation(Libs.DebugCompose.uiTooling)
    debugImplementation(Libs.DebugCompose.uiTestManifest)
}