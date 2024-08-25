plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
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
    )

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.coroutines)
            // Dependency injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeViewModel)
            implementation(libs.kotlinX.serializationJson)
            implementation(libs.sqlDelight.coroutine)
            implementation(libs.navigation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.android)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.native)
        }
    }
}

android {
    namespace = "com.example.noteapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.shared.data.cache.sqldelight")
            srcDirs.setFrom("src/commonMain/kotlin")
        }
    }
}