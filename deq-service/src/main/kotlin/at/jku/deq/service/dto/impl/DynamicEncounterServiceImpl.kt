package at.jku.deq.service.dto.impl

import at.jku.deq.api.dto.RecommendationDto
import at.jku.deq.api.dto.RecommendationResponseDto
import at.jku.deq.service.config.XpConfig
import at.jku.deq.service.dto.DynamicEncounterService
import at.jku.deq.service.mapper.toDto
import at.jku.deq.service.service.RecommendationService
import at.jku.deq.service.service.SyncService
import org.springframework.stereotype.Service

@Service
internal class DynamicEncounterServiceImpl(
    private val syncService: SyncService,
    private val recommendationService: RecommendationService,
    private val xpConfig: XpConfig
) : DynamicEncounterService {
    override fun sync() {
        syncService.sync()
    }

    override fun recommendation(recommendationDto: RecommendationDto): RecommendationResponseDto {
        return recommendationService.recommend(recommendationDto)
            .toDto(xpConfig.xpPerCrId)
    }
}