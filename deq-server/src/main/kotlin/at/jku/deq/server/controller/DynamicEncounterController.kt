package at.jku.deq.server.controller

import at.jku.deq.api.DynamicEncounterApi
import at.jku.deq.api.dto.RecommendationDto
import at.jku.deq.api.dto.RecommendationResponseDto
import at.jku.deq.service.dto.DynamicEncounterService
import org.springframework.web.bind.annotation.RestController

@RestController
internal class DynamicEncounterController(
    private val dynamicEncounterService: DynamicEncounterService
) : DynamicEncounterApi {
    override fun sync() {
        dynamicEncounterService.sync()
    }

    override fun recommendation(filter: RecommendationDto): RecommendationResponseDto {
        return dynamicEncounterService.recommendation(filter)
    }
}