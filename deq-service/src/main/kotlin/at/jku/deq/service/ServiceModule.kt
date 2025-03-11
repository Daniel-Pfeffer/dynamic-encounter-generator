package at.jku.deq.service

import at.jku.deq.ddbeyond.DNDBeyondModule
import at.jku.deq.domain.DomainModule
import at.jku.deq.service.config.XpConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@ComponentScan
@Import(DNDBeyondModule::class, DomainModule::class)
@EnableConfigurationProperties(XpConfig::class)
class ServiceModule