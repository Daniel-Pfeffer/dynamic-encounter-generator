package at.jku.deq.ddbeyond.api.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class AuthDto(
    val token: String,
    val ttl: Int
)