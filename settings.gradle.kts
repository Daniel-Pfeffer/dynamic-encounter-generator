plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "deq"

include("deq-api")
include("deq-domain")
include("deq-server")
include("deq-service")
include("deq-ddbeyond")
include("deq-security")
include("deq-testFixtures")
