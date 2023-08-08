plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.countries.ui_components"
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
    implementation(project(":libraries:testing"))
    implementation(project(":libraries:extensions"))

    implementation(Dependencies.Main.appcompat)
    implementation(Dependencies.Main.constraint_layout)

    //Unit Test
    testImplementation(Dependencies.Testing.robolectric)

    //Instrumental Test
    androidTestImplementation(Dependencies.Testing.assertK)
    androidTestImplementation(Dependencies.Testing.robolectric)
    androidTestImplementation(Dependencies.Testing.mockk)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.ext_junit)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.test_runner)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.espresso_core)
}