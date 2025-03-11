package at.jku.deq.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationResponseDto(
    val difficulty: EncounterDifficultyEnum,
    val xp: Long,
    val monsters: List<MonsterDto>
)


@Serializable
data class RecommendationDto(
    val party: Party? = null,
    val encounterDifficulty: EncounterDifficulty,
    val filter: MonsterFilter? = null
)

// requires one of
@Serializable
data class EncounterDifficulty(
    val xp: Long? = null,
    val difficulty: EncounterDifficultyEnum? = null,
)

@Serializable
data class Party(
    val size: Long,
    val level: Long,
)

enum class EncounterDifficultyEnum {
    Low,
    Moderate,
    High,
}