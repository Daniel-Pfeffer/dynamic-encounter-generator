package at.jku.deq.service.service

import at.jku.deq.api.dto.EncounterDifficultyEnum
import at.jku.deq.api.dto.Party
import at.jku.deq.api.dto.RecommendationDto
import at.jku.deq.domain.entity.Language
import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.model.MonsterFilter
import at.jku.deq.domain.service.MonsterRecommendationService
import at.jku.deq.service.config.Difficulty
import at.jku.deq.service.config.XpConfig
import at.jku.deq.service.mapper.toDomain
import at.jku.deq.service.model.Recommendation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.RoundingMode
import kotlin.math.ceil

@Service
internal class RecommendationService(
    private val monsterRecommendationService: MonsterRecommendationService,
    private val xpConfig: XpConfig
) {

    companion object {
        private const val MAX_MONSTERS = 6
        private const val BOSS_MULTIPLIER = 0.5f
    }

    @Transactional(readOnly = true)
    fun recommend(recommendationDto: RecommendationDto): Recommendation {
        val maxXp = (
                recommendationDto.encounterDifficulty.xp
                    ?: recommendationDto.party?.calculateXp(
                        xpConfig,
                        recommendationDto.encounterDifficulty.difficulty?.toDifficulty()
                            ?: Difficulty.Moderate
                    )
                    ?: recommendationDto.filter?.challengeRating
                    ?: throw IllegalArgumentException("Either encounter difficulty and xp or party and difficulty must be provided")
                ).toLong()


        val initialFilter = MonsterFilter(
            name = recommendationDto.filter?.name,
            type = setOfNotNull(recommendationDto.filter?.type?.toDomain()),
            language = setOfNotNull(recommendationDto.filter?.languages?.let { Language.valueOf(it.first()) }),
            alignment = setOfNotNull(recommendationDto.filter?.alignment?.toDomain()),
            size = setOfNotNull(recommendationDto.filter?.size?.toDomain()),
            environment = setOfNotNull(recommendationDto.filter?.environments?.first()?.toDomain()),
        )

        val bossMonster = getBossRecommendation(initialFilter, maxXp)

        val remainingXp = maxXp - xpConfig.getXp(bossMonster.challengeRating)

        val remainingMonsters = MAX_MONSTERS - 1

        val xpPerMonster = remainingXp / remainingMonsters

        val newCr = approximateXpToCr(xpPerMonster, xpConfig)


        val restRecommendations = monsterRecommendationService.findByExample(
            filter = initialFilter.copy(
                environment = setOf(bossMonster.environments, initialFilter.environment)
                    .filterNotNull()
                    .flatMapTo(HashSet()) { it },
                language = bossMonster.languages,
                challengeRating = newCr
            )
        )

        val monsters = listOf(bossMonster) + restRecommendations.take(remainingMonsters)

        val totalXp = monsters.calculateXp(xpConfig)

        return Recommendation(
            monsters = monsters,
            xp = totalXp,
            difficulty = recommendationDto.party?.let { approximateChallengeFromXp(totalXp, xpConfig, it) }
                ?: Difficulty.None
        )
    }

    private fun getBossRecommendation(filter: MonsterFilter, maxXp: Long): Monster {
        val bossXp = ceil(maxXp * BOSS_MULTIPLIER).toLong()

        val bossCr = approximateXpToCr(bossXp, xpConfig)
        return monsterRecommendationService.findByExample(
            filter = filter.copy(challengeRating = bossCr)
        ).first()
    }

    private fun approximateChallengeFromXp(xp: Long, xpConfig: XpConfig, party: Party): Difficulty {
        xpConfig.encounterDifficulty.first { it.partyLevel == party.level }.let { partyXp ->
            val xpPerPlayer = xp / party.size
            val difficulty = xpPerPlayer / partyXp.difficulty.getValue(Difficulty.Moderate)
            return when {
                difficulty < 0.5 -> Difficulty.Low
                difficulty < 1.5 -> Difficulty.Moderate
                else -> Difficulty.High
            }
        }
    }


    private fun List<Monster>.calculateXp(xpConfig: XpConfig): Long {
        return this.sumOf { xpConfig.xpPerCrId.getValue(it.challengeRating) }.toLong()
    }

    private fun approximateXpToCr(xp: Long, xpConfig: XpConfig, roundingMode: RoundingMode = RoundingMode.UP): Int {
        val crXpEntries = xpConfig.xpPerCrId.entries.toList().windowed(2)
            .first { (start, end) ->
                xp in start.value..end.value
            }

        val (cr, _) = if (roundingMode == RoundingMode.UP) {
            crXpEntries[1]
        } else crXpEntries.first()

        return cr
    }


    fun EncounterDifficultyEnum.toDifficulty(): Difficulty? {
        return when (this) {
            EncounterDifficultyEnum.Low -> Difficulty.Low
            EncounterDifficultyEnum.Moderate -> Difficulty.Moderate
            EncounterDifficultyEnum.High -> Difficulty.High
        }
    }

    fun Party.calculateXp(xpConfig: XpConfig, difficulty: Difficulty): Long {
        val partyXp = xpConfig.encounterDifficulty.first { it.partyLevel == this.level }
        return partyXp.difficulty.getValue(difficulty) * this.size
    }

}