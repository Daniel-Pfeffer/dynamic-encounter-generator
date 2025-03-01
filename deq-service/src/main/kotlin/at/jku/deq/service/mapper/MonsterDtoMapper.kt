package at.jku.deq.service.mapper

import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.domain.entity.Monster

fun Monster.toDto(): MonsterDto = MonsterDto(
    id = id,
    name = name,
    url = url,
    avatarUrl = avatarUrl,
    description = description,
    size = size.name,
    alignment = possibleAlignments.toString(),
    stats = stats.map { it.key.name to it.value }.toMap(),
    armorClass = armorClass,
    challengeRatingId = challengeRating,
    xp = challengeRating * 100L,
    environments = environments.map { it.name }.toSet(),
    languages = languages.map { it.name }.toSet(),
)