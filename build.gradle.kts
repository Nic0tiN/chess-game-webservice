plugins {
	java
	application
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("io.freefair.lombok") version "8.6"
}

group = "com.clic-k-tic"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	flatDir {
		//dirs("/home/gradle/chessgame-ws/")
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
	//implementation(files("/home/gradle/chessgame-ws/GameChess.jar"))
	implementation(files("c:\\devs\\java\\Game-Chess\\out\\artifacts\\Game_Chess_jar\\Game-Chess.jar"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.session:spring-session-data-redis")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
