dependencies {
    implementation(project(":deq-api"))
    implementation(project(":deq-service"))
    implementation(project(":deq-security"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")

    // for advice handler
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
