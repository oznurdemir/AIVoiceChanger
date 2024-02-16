buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}
plugins {
    id ("com.android.application") version "7.4.2" apply false
    id ("com.android.library" ) version "7.4.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id ("androidx.navigation.safeargs") version "2.4.2" apply false
}