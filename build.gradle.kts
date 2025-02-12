import org.jetbrains.intellij.platform.gradle.tasks.RunIdeTask

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("org.jetbrains.intellij.platform")
}

group = "com.frybits"
version = "1.0-SNAPSHOT"

dependencies {
    intellijPlatform {
        androidStudio("2024.1.1.3")
        plugin("org.jetbrains.android:241.14494.240")
    }
}

tasks {
    withType<RunIdeTask> {
        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf("-Didea.kotlin.plugin.use.k2=true")
        }
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("243.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
