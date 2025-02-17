package at.jku.deq.ddbeyond.mapper

import at.jku.deq.ddbeyond.api.dto.DndBeyondMonster
import at.jku.deq.ddbeyond.dto.Alignment
import at.jku.deq.ddbeyond.dto.DeqMonster

internal fun DndBeyondMonster.toDeqMonster() = DeqMonster(
    id = this.id,
    name = this.name,
    url = this.url,
    description = this.characteristicsDescription,
    sizeId = this.sizeId,
    alignment = Alignment.fromId(this.alignmentId),
    stats = emptyMap(),
    armorClass = this.armorClass,
    challengeRatingId = this.challengeRatingId,
    typeId = this.typeId,
)