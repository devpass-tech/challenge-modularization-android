import java.util.Properties
import java.io.FileInputStream

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1"
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.devpass.spaceapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

     val localProperties = Properties()
     localProperties.load(FileInputStream(rootProject.file("local.properties")))

    buildTypes {
        release {
           isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            manifestPlaceholders["MAPS_API_KEY"] = localProperties["maps_api_key"] as String
        }
        debug {
            isMinifyEnabled = false
            manifestPlaceholders["MAPS_API_KEY"] = localProperties["maps_api_key"] as String
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.lifecycle)
    implementation(libs.activity.ktx)
    implementation(libs.google.maps)
    implementation(libs.bundles.retrofit)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.junit.android)
}