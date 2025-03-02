package at.jku.deq.ddbeyond.config

import feign.codec.Decoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.springframework.context.annotation.Bean

internal class GlobalFeignConfig {

    @Bean
    fun feignDecoder(): Decoder {
        return Decoder { response, type ->
            val format = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            val serializer = format.serializersModule.serializer(type)

            val body = response.body().asReader(response.charset()).use { it.readText() }

            format.decodeFromString(serializer, body)
        }
    }
}