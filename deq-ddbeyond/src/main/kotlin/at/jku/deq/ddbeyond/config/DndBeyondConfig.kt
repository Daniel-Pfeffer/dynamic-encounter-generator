package at.jku.deq.ddbeyond.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("dndbeyond")
internal data class DndBeyondConfig(
    val cobaltToken: String
)