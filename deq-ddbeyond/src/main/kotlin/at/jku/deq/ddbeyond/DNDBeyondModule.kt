package at.jku.deq.ddbeyond

import at.jku.deq.ddbeyond.config.DndBeyondConfig
import at.jku.deq.ddbeyond.config.GlobalFeignConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan


@EnableFeignClients(defaultConfiguration = [GlobalFeignConfig::class])
@EnableConfigurationProperties(DndBeyondConfig::class)
@ComponentScan
class DNDBeyondModule