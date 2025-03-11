package at.jku.deq.domain

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Import

@SpringBootConfiguration
@Import(DomainModule::class)
internal class TestConfiguration