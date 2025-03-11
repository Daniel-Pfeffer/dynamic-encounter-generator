package at.jku.deq.api

import at.jku.deq.api.dto.RecommendationDto
import at.jku.deq.api.dto.RecommendationResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface DynamicEncounterApi {
    @PostMapping("sync")
    fun sync()

    @PostMapping("recommendation")
    fun recommendation(@RequestBody filter: RecommendationDto): RecommendationResponseDto
}