import org.jetbrains.kotlin.gradle.tasks.KotlinCompile




plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.parallels"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}


dependencies {
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.natpryce:konfig:1.6.10.0")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.eclipse.paho:org.eclipse.paho.mqttv5.client:1.2.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}


tasks.register<Copy>("copyToNets") {
    println("Start copy files to net folder")
    from(layout.buildDirectory.dir(buildDir.toString() + "/classes/kotlin/main"))
    into(layout.buildDirectory.dir("/home/parallels/nets/"))
    println("copy done")
}






