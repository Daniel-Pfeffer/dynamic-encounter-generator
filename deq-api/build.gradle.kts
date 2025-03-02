dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("org.springframework:spring-web")
    implementation("io.swagger.core.v3:swagger-annotations")

    testFixturesImplementation(project(":deq-testFixtures"))
}