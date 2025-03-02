package at.jku.deq.service.mapper

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.StatDto
import at.jku.deq.domain.entity.*

fun Monster.toDto(): MonsterDto = MonsterDto(
    id = id,
    name = name,
    url = url,
    avatarUrl = avatarUrl,
    description = description,
    size = size.name,
    alignment = possibleAlignments.toString(),
    stats = stats.toDto(),
    armorClass = armorClass,
    challengeRatingId = challengeRating,
    xp = challengeRating * 100L,
    environments = environments.map { it.name }.toSet(),
    languages = languages.map { it.name }.toSet(),
)

fun Map<StatType, Long>.toDto() = StatDto(
    strength = getOrDefault(StatType.STR, 0),
    dexterity = getOrDefault(StatType.DEX, 0),
    constitution = getOrDefault(StatType.CON, 0),
    intelligence = getOrDefault(StatType.INT, 0),
    wisdom = getOrDefault(StatType.WIS, 0),
    charisma = getOrDefault(StatType.CHA, 0),
)

fun StatDto.toDomain() = mapOf(
    StatType.STR to strength,
    StatType.DEX to dexterity,
    StatType.CON to constitution,
    StatType.INT to intelligence,
    StatType.WIS to wisdom,
    StatType.CHA to charisma,
)

fun CreateMonsterDto.toMonster() = Monster(
    name = name,
    url = url,
    avatarUrl = avatarUrl,
    description = description,
    size = Size.valueOf(size),
    type = MonsterType.valueOf(type),
    possibleAlignments = setOf(Alignment.valueOf(this.alignment)),
    stats = stats.toDomain(),
    armorClass = armorClass.toInt(),
    challengeRating = challengeRatingId.toInt(),
    environments = environments.map { Environment.valueOf(it) }.toSet(),
    languages = languages.map { Language.valueOf(it) }.toSet(),
    externalId = null,
    source = Monster.Source.MANUAL,
)