package at.jku.deq.server

import at.jku.deq.security.SecurityModule
import at.jku.deq.service.ServiceModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@Import(ServiceModule::class, SecurityModule::class)
class DynamicEncounterGeneratorApplication

fun main(args: Array<String>) {
    runApplication<DynamicEncounterGeneratorApplication>(*args)
}
