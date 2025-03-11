package at.jku.deq.service.dto

import at.jku.deq.api.dto.RecommendationDto
import at.jku.deq.api.dto.RecommendationResponseDto

interface DynamicEncounterService {
    fun sync()

    fun recommendation(recommendationDto: RecommendationDto): RecommendationResponseDto
}