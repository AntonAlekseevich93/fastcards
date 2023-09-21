
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String  by project

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    application
}

group = "com.fastcards"
version = "0.0.1"

application {
    mainClass.set("com.fastcards.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.fastcards.ApplicationKt"
    }

}

//ktor {
//    fatJar {
//        archiveFileName.set("com.fastcards.fastcards.backend-0.0.1.jar")
//    }
//}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

//tasks {
//    create("stage").dependsOn("installDist")
//}



dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
//    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")


    implementation("org.postgresql:postgresql:42.2.2")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
