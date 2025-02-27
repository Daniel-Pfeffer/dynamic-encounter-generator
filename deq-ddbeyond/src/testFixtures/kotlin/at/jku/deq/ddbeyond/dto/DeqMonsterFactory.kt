package at.jku.deq.ddbeyond.dto

import at.jku.deq.shared.AbstractFactory

class DeqMonsterFactory(post: (DeqMonster) -> DeqMonster = { it }) : AbstractFactory<DeqMonster>(post) {
    fun create(
        id: Long = 1L,
        name: String = "Monster",
        url: String = "https://monster",
        avatarUrl: String = "https://monster/avatar",
        description: String = "Some monster",
        size: DDBSize = DDBSize.TINY,
        type: DDBMonsterType = DDBMonsterType.FEY,
        alignment: DDBAlignment = DDBAlignment.AnyChaoticAlignment,
        stats: Map<DDBStatType, Long> = emptyMap(),
        armorClass: Long = 12,
        challengeRatingId: Long = 3,
        environments: Set<DDBEnvironment> = setOf(DDBEnvironment.ElementalChaos),
        languages: Set<DDBLanguage> = setOf(DDBLanguage.celestial)
    ): DeqMonster {
        return createInternal(
            {}, DeqMonster(
                id = id,
                name = name,
                url = url,
                avatarUrl = avatarUrl,
                description = description,
                size = size,
                type = type,
                alignment = alignment,
                stats = stats,
                armorClass = armorClass,
                challengeRatingId = challengeRatingId,
                environments = environments,
                languages = languages
            )
        )
    }
}