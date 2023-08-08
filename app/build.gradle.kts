plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("kotlin-kapt")
    id("jacoco-reports")
}

android {
    namespace = "br.com.countries"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.countries"
        minSdk = 27
        targetSdk = 33
        targetSdkPreview = "33"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    tasks.withType<Test> {
        extensions.configure(JacocoTaskExtension::class) {
            isIncludeNoLocationClasses = true
            excludes = listOf("jdk.internal.*")
        }
    }
}

dependencies {

    implementation(project(":libraries:network"))
    implementation(project(":libraries:extensions"))
    implementation(project(":libraries:testing"))
    implementation(project(":libraries:ui-components"))

    //Kotlin
    implementation(Dependencies.Kotlin.kotlin)
    implementation(Dependencies.Kotlin.kotlinLib)

    //Core and Material Design
    implementation(Dependencies.Main.core_ktx)
    implementation(Dependencies.Main.appcompat)
    implementation(Dependencies.Main.material)
    implementation(Dependencies.Main.constraint_layout)
    implementation(Dependencies.Main.swipe_refresh_layout)

    //Lifecycle
    implementation(Dependencies.Main.Lifecycle.lifecycle_livedata)
    implementation(Dependencies.Main.Lifecycle.lifecycle_viewmodel)
    implementation(Dependencies.Main.Lifecycle.lifecycle_runtime)

    //ThirdParty
    implementation(Dependencies.ThirdParty.glide_compiler)
    implementation(Dependencies.ThirdParty.glide)
    implementation(Dependencies.ThirdParty.koin)
    implementation(Dependencies.ThirdParty.facebook_shimmer)

    //Network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converter_gson)
    implementation(Dependencies.Network.okhttp3_logging_interceptor)

    //Unit Test
    testImplementation(Dependencies.Testing.assertK)
    testImplementation(Dependencies.Testing.robolectric)
    testImplementation(Dependencies.Testing.mockk)
    testImplementation(Dependencies.Testing.junit)
    testImplementation(Dependencies.Testing.koin_test)
    testImplementation(Dependencies.Testing.mockwebserver)

    //Instrumental Test
    androidTestImplementation(Dependencies.Testing.assertK)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.core_test)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.mockk_android)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.ext_junit)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.barista)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.espresso_core)

}