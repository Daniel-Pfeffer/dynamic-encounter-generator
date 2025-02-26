package at.jku.deq.ddbeyond.mapper

import at.jku.deq.ddbeyond.api.dto.DndBeyondMonster
import at.jku.deq.ddbeyond.dto.*

internal fun DndBeyondMonster.toDeqMonster() = DeqMonster(
    id = this.id,
    name = this.name,
    url = this.url,
    avatarUrl = this.avatarUrl,
    description = this.characteristicsDescription,
    size = fromId(this.sizeId),
    alignment = this.alignmentId?.let { fromId<DDBAlignment>(it) } ?: DDBAlignment.Unaligned,
    stats = this.stats.associate { fromId<DDBStatType>(it.statId) to it.value },
    armorClass = this.armorClass,
    challengeRatingId = this.challengeRatingId,
    type = fromId(this.typeId),
    environments = this.environments.map { fromId<DDBEnvironment>(it) }.toSet(),
    languages = this.languages.map { fromId<DDBLanguage>(it.languageId) }.toSet()
)