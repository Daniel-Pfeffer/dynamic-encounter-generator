package at.jku.deq.domain

import at.jku.deq.domain.entity.*
import at.jku.deq.shared.AbstractFactory

class MonsterFactory(post: (Monster) -> Monster = { it }) : AbstractFactory<Monster>(post) {
    fun create(
        externalId: Long? = null,
        name: String = "Monster",
        url: String = "http://monster.com",
        avatarUrl: String = "http://monster.com/avatar",
        description: String = "A monster",
        size: Size = Size.MEDIUM,
        type: MonsterType = MonsterType.ABERRATION,
        stats: Map<StatType, Long> = emptyMap(),
        armorClass: Int = 10,
        challengeRating: Int = 1,
        environments: Set<Environment> = emptySet(),
        languages: Set<Language> = emptySet(),
        possibleAlignments: Set<Alignment> = emptySet(),
        source: Monster.Source = Monster.Source.MANUAL,
        objectCustomizer: Monster.() -> Unit = { }
    ): Monster {
        return createInternal(
            objectCustomizer,
            Monster(
                externalId = externalId,
                name = name,
                url = url,
                avatarUrl = avatarUrl,
                description = description,
                size = size,
                type = type,
                stats = stats,
                armorClass = armorClass,
                challengeRating = challengeRating,
                environments = environments,
                languages = languages,
                possibleAlignments = possibleAlignments,
                source = source
            )
        )
    }
}
