import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id( "kotlin-kapt")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.abi.pillsreminderabi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.abi.pillsreminderabi"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            exclude("META-INF/LICENSE.md")
            exclude("META-INF/NOTICE.md")
        }
    }
}
dependencies {
    val nav_version = "2.5.3"
    val room_version = "2.5.0"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    ksp("androidx.room:room-compiler:$room_version")



    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    // Allow references to generated code
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:$nav_version")
    //GSON
    implementation ("com.google.code.gson:gson:2.9.0")
    //BLE
    implementation ("com.github.LeandroSQ:android-ble-made-easy:1.8.2")
    //ASYNC image
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("commons-io:commons-io:2.7")
    //Permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.21.1-beta")
    // Location Services
    //implementation ("com.google.android.gms:play-services-location")

    implementation ("androidx.compose.material:material-icons-extended:1.4.3")

}
kapt {
    correctErrorTypes = true
}