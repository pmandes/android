import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

val accuWeatherApiKey = localProperties.getProperty("ACCUWEATHER_API_KEY")
    ?: throw GradleException("AccuWeather API key is missing. Please add ACCUWEATHER_API_KEY to the local.properties file.")

android {
    namespace = "pl.madsoft.myweather.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "ACCUWEATHER_API_KEY", "\"$accuWeatherApiKey\"")
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

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    //Hilt
    implementation(libs.hilt)
    implementation(libs.core.ktx)
    ksp(libs.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.logging.interceptor)

    //Moshi
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    //Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    //Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)

    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.room.testing)
}