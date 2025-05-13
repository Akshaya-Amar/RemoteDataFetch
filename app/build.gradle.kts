plugins {
     alias(libs.plugins.android.application)
     alias(libs.plugins.kotlin.android)
     alias(libs.plugins.kotlin.kapt)
     alias(libs.plugins.hilt.android)
}

android {
     namespace = "com.amar.apidemomvvm"
     compileSdk = 35

     defaultConfig {
          applicationId = "com.amar.apidemomvvm"
          minSdk = 24
          targetSdk = 35
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
          }
     }

     compileOptions {
          sourceCompatibility = JavaVersion.VERSION_11
          targetCompatibility = JavaVersion.VERSION_11
     }

     kotlinOptions {
          jvmTarget = "11"
     }

     viewBinding {
          enable = true
     }
}

dependencies {
     implementation(libs.androidx.core.ktx)
     implementation(libs.androidx.appcompat)
     implementation(libs.material)
     implementation(libs.retrofit2.retrofit)
     implementation(libs.converter.gson)
     implementation(libs.hilt.android)
     implementation(libs.androidx.activity)
     implementation(libs.androidx.constraintlayout)
     kapt(libs.hilt.android.compiler)
     implementation(libs.androidx.lifecycle.viewmodel.ktx)
     implementation(libs.androidx.navigation.fragment.ktx)
     implementation(libs.androidx.navigation.ui.ktx)
     implementation(libs.logging.interceptor)
     implementation(libs.androidx.paging.runtime.ktx)
     testImplementation(libs.junit)
     androidTestImplementation(libs.androidx.junit)
     androidTestImplementation(libs.androidx.espresso.core)
}