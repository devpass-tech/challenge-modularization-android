// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias (libs.plugins.android.application) apply false
    alias (libs.plugins.android.library) apply false
    alias (libs.plugins.android.kotlin) apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks.register("clean").configure {
    delete("build")
}