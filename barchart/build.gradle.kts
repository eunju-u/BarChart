import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.vanniktech.maven)
}

android {
    namespace = "com.eunjulee.barchart"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
}

mavenPublishing {
    // implementation("io.github.eunju-u:compose-bar-chart:1.0.0")
    coordinates(
        groupId = "io.github.eunju-u", // namespace
        artifactId = "compose-bar-chart", // library artifact id
        version = "1.0.0" // version
    )

    pom {
        name.set("ComposeBarChart") // library name
        description.set("BarChart made with Compose UI") // library description
        inceptionYear.set("2024") // The year the library was created
        url.set("https://github.com/eunju-u/ComposeBarChart") // library distribution url

        licenses {
            license { // License name and license information url settings
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer { // Developer information
                id.set("eunju-u")
                name.set("eunjulee")
                email.set("97deer@gmail.com")
            }
        }

        scm { // source code management information
            connection.set("scm:git:git://github.com/eunju-u/ComposeBarChart.git")
            developerConnection.set("scm:git:ssh://github.com:eunju-u/ComposeBarChart.git")
            url.set("https://github.com/eunju-u/ComposeBarChart")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL) // Maven Central distribution location

    signAllPublications() // distribution gpg signing
}