package at.jku.deq.service

import at.jku.deq.ddbeyond.DNDBeyondModule
import at.jku.deq.domain.DomainModule
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@ComponentScan
@Import(DNDBeyondModule::class, DomainModule::class)
class ServiceModule