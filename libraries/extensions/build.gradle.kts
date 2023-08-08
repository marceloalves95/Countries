plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.countries.extensions"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":libraries:network"))

    implementation(Dependencies.Main.appcompat)

    //Lifecycle
    implementation(Dependencies.Main.Lifecycle.lifecycle_livedata)
    implementation(Dependencies.Main.Lifecycle.lifecycle_viewmodel)
    implementation(Dependencies.Main.Lifecycle.lifecycle_runtime)

    //Network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converter_gson)
    implementation(Dependencies.Network.okhttp3_logging_interceptor)

    //ThirdParty
    implementation(Dependencies.ThirdParty.glide)
    implementation(Dependencies.ThirdParty.facebook_shimmer)
}