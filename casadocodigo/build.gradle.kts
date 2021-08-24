plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    id("org.jetbrains.kotlin.kapt") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "2.0.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
}

version = "0.1"
group = "br.com.zupacademy"
//kotlinVersion = "1.5.21"

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("br.com.zupacademy.*")
    }
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor:2.5.0")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.micronaut.xml:micronaut-jackson-xml")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    //JPA
    implementation("io.micronaut.sql:micronaut-jdbc-hikari:3.4.0")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa:2.5.0")
    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
    //implementation("io.micronaut.configuration:micronaut-hibernate-jpa:1.0.2")
    implementation("org.hibernate:hibernate-entitymanager:5.5.6.Final")

    //DATA BASE
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:testcontainers")


}

allOpen {
    annotation("io.micronaut.http.annotation.Controller")
}

application {
    mainClass.set("br.com.zupacademy.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

}
