package at.jku.deq.ddbeyond.api.config

import at.jku.deq.ddbeyond.api.client.AuthApiClient
import at.jku.deq.ddbeyond.config.DndBeyondConfig
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

internal class DndBeyondFeignConfig {

    @Bean
    fun authInterceptor(tokenCache: TokenCache): RequestInterceptor {
        return RequestInterceptor { template ->
            template.header("Authorization", "Bearer ${tokenCache.getToken()}")
        }
    }

    @Component
    internal class TokenCache(
        private val authApiClient: AuthApiClient,
        private val dndConfig: DndBeyondConfig
    ) {
        private var requestNew: LocalDateTime = LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault())

        private lateinit var token: String

        fun getToken(): String {
            val now = LocalDateTime.now()
            if (requestNew.isBefore(now)) {
                val auth = authApiClient.getToken(dndConfig.cobaltToken)
                token = auth.token
                requestNew = now.plusSeconds(auth.ttl.toLong())
            }
            return token
        }
    }
}