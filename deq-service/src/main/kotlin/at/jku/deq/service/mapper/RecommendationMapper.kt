package at.jku.deq.service.mapper

import at.jku.deq.api.dto.EncounterDifficultyEnum
import at.jku.deq.api.dto.RecommendationResponseDto
import at.jku.deq.service.model.Recommendation

internal fun Recommendation.toDto(xpPerCrId: Map<Int, Int>): RecommendationResponseDto = RecommendationResponseDto(
    difficulty = EncounterDifficultyEnum.High,
    xp = this.xp,
    monsters = this.monsters.map { it.toDto(xpPerCrId) }
)