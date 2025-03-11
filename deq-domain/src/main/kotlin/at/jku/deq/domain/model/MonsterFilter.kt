package at.jku.deq.domain.model

import at.jku.deq.domain.entity.*

data class MonsterFilter(
    val name: String? = null,
    val type: Set<MonsterType>? = null,
    val language: Set<Language>? = null,
    val challengeRating: Int? = null,
    val alignment: Set<Alignment>? = null,
    val size: Set<Size>? = null,
    val environment: Set<Environment>? = null
)
