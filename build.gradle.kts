plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.clic-k-tic"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	flatDir {
		dirs("c:\\devs\\java\\Game-Chess\\out\\artifacts\\Game_Chess_jar")
	}
}
configurations {
	create("externalLib")
	developmentOnly
	runtimeClasspath {
		extendsFrom (configurations.developmentOnly.get())
	}
}
dependencies {
	implementation(files("c:\\devs\\java\\Game-Chess\\out\\artifacts\\Game_Chess_jar\\Game-Chess.jar"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
