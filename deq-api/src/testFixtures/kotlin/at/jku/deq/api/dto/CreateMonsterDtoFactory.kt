package at.jku.deq.api.dto

import at.jku.deq.shared.AbstractFactory

class CreateMonsterDtoFactory(
    private val statDtoFactory: StatDtoFactory = StatDtoFactory(),
    postProcessor: (CreateMonsterDto) -> CreateMonsterDto = { it }
) : AbstractFactory<CreateMonsterDto>(postProcessor) {
    fun create(
        id: Long = 1,
        name: String = "Monster",
        url: String? = null,
        avatarUrl: String? = null,
        description: String = "I am a Monster",
        size: String = "MEDIUM",
        alignment: String = "NEUTRAL",
        stats: StatDto = statDtoFactory.create(),
        armorClass: Long = 10,
        challengeRatingId: Long = 1,
        environments: Set<String> = emptySet(),
        languages: Set<String> = emptySet(),
        type: String = "BEAST",
    ): CreateMonsterDto {
        return createInternal(
            {}, CreateMonsterDto(
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
                environments = environments,
                languages = languages,
                type = type
            )
        )
    }
}