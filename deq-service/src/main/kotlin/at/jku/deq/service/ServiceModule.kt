package at.jku.deq.service

import at.jku.deq.ddbeyond.DNDBeyondModule
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@ComponentScan
@Import(DNDBeyondModule::class)
class ServiceModule