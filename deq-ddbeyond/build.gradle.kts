dependencies {
    implementation("org.springframework:spring-web")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")

    testFixturesImplementation(project(":deq-testFixtures"))
    testImplementation(kotlin("test"))
}
