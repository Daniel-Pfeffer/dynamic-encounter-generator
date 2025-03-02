package at.jku.deq.api.dto

import at.jku.deq.shared.AbstractFactory

class MonsterDtoFactory(
    private val statDtoFactory: StatDtoFactory = StatDtoFactory(),
    postProcessor: (MonsterDto) -> MonsterDto = { it }
) : AbstractFactory<MonsterDto>(postProcessor) {
    fun create(
        id: Long = 1,
        name: String = "Monster",
        url: String? = null,
        avatarUrl: String? = null,
        description: String = "I am a Monster",
        size: String = "MEDIUM",
        alignment: String = "NEUTRAL",
        stats: StatDto = statDtoFactory.create(),
        armorClass: Int = 10,
        challengeRatingId: Int = 1,
        environments: Set<String> = emptySet(),
        languages: Set<String> = emptySet(),
    ): MonsterDto {
        return createInternal(
            {}, MonsterDto(
                id = id,
                name = name,
                url = url,
                avatarUrl = avatarUrl,
                description = description,
                size = size,
                alignment = alignment,
                stats = stats,
                armorClass = armorClass,
                challengeRatingId = challengeRatingId,
                xp = challengeRatingId * 100L,
                environments = environments,
                languages = languages
            )
        )
    }
}