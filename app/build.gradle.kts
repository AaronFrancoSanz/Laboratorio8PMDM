plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.laboratorio8pmdm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.laboratorio8pmdm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // ExoPlayer (Media3): reproductor y PlayerView
    implementation("androidx.media3:media3-exoplayer:1.5.1")
    implementation("androidx.media3:media3-ui:1.5.1")
}
