plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.22"
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val coroutinesVersion = "1.6.4"
        val ktorVersion = "2.2.4"
        val lifecycle_version = "2.6.1"


        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                //KTOR
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // KMM Viewmodel https://github.com/rickclephas/KMM-ViewModel
                api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-7")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
            }
        }
    }
}

android {
    namespace = "com.vis.myapplication"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}