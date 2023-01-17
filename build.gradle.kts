plugins {
	java
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.raychenon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val junitJupiter = "5.8.1"
val slf4j = "2.0.6"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.slf4j:slf4j-api:${slf4j}")
	implementation("org.slf4j:slf4j-simple:${slf4j}")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:${junitJupiter}")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitJupiter}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
