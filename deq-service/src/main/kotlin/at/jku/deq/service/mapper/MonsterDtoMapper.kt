package at.jku.deq.service.mapper

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.StatDto
import at.jku.deq.domain.entity.*
import at.jku.deq.api.dto.Alignment as ApiDtoAlignment
import at.jku.deq.api.dto.Environment as ApiDtoEnvironment
import at.jku.deq.api.dto.MonsterType as ApiDtoMonsterType
import at.jku.deq.api.dto.Size as ApiDtoSize

fun Monster.toDto(xpConfig: Map<Int, Int>): MonsterDto = MonsterDto(
    id = id,
    name = name,
    url = url,
    avatarUrl = avatarUrl,
    description = description,
    size = size.toDto(),
    alignments = possibleAlignments.mapTo(HashSet()) { it.toDto() },
    stats = stats.toDto(),
    armorClass = armorClass,
    challengeRatingId = challengeRating,
    xp = xpConfig.getValue(challengeRating).toLong(),
    environments = environments.map { it.toDto() }.toSet(),
    languages = languages.map { it.name }.toSet(),
    type = type.toDto()
)

fun MonsterType.toDto(): ApiDtoMonsterType = when (this) {
    MonsterType.ABERRATION -> ApiDtoMonsterType.ABERRATION
    MonsterType.BEAST -> ApiDtoMonsterType.BEAST
    MonsterType.CELESTIAL -> ApiDtoMonsterType.CELESTIAL
    MonsterType.CONSTRUCT -> ApiDtoMonsterType.CONSTRUCT
    MonsterType.DRAGON -> ApiDtoMonsterType.DRAGON
    MonsterType.ELEMENTAL -> ApiDtoMonsterType.ELEMENTAL
    MonsterType.FEY -> ApiDtoMonsterType.FEY
    MonsterType.FIEND -> ApiDtoMonsterType.FIEND
    MonsterType.GIANT -> ApiDtoMonsterType.GIANT
    MonsterType.HUMANOID -> ApiDtoMonsterType.HUMANOID
    MonsterType.MONSTROSITY -> ApiDtoMonsterType.MONSTROSITY
    MonsterType.OOZE -> ApiDtoMonsterType.OOZE
    MonsterType.PLANT -> ApiDtoMonsterType.PLANT
    MonsterType.UNDEAD -> ApiDtoMonsterType.UNDEAD
}

fun Environment.toDto(): ApiDtoEnvironment = when (this) {
    Environment.Any -> ApiDtoEnvironment.Any
    Environment.Arctic -> ApiDtoEnvironment.Arctic
    Environment.Coastal -> ApiDtoEnvironment.Coastal
    Environment.Desert -> ApiDtoEnvironment.Desert
    Environment.Forest -> ApiDtoEnvironment.Forest
    Environment.Grassland -> ApiDtoEnvironment.Grassland
    Environment.Hill -> ApiDtoEnvironment.Hill
    Environment.Mountain -> ApiDtoEnvironment.Mountain
    Environment.Abyss -> ApiDtoEnvironment.Abyss
    Environment.Acheron -> ApiDtoEnvironment.Acheron
    Environment.AstralPlane -> ApiDtoEnvironment.AstralPlane
    Environment.Beastlands -> ApiDtoEnvironment.Beastlands
    Environment.ElementalChaos -> ApiDtoEnvironment.ElementalChaos
    Environment.ElementalAir -> ApiDtoEnvironment.ElementalAir
    Environment.ElementalEarth -> ApiDtoEnvironment.ElementalEarth
    Environment.ElementalFire -> ApiDtoEnvironment.ElementalFire
    Environment.ElementalWater -> ApiDtoEnvironment.ElementalWater
    Environment.ElementalPlanes -> ApiDtoEnvironment.ElementalPlanes
    Environment.EtherealPlane -> ApiDtoEnvironment.EtherealPlane
    Environment.Feywild -> ApiDtoEnvironment.Feywild
    Environment.Gehenna -> ApiDtoEnvironment.Gehenna
    Environment.Limbo -> ApiDtoEnvironment.Limbo
    Environment.LowerPlanes -> ApiDtoEnvironment.LowerPlanes
    Environment.Mechanus -> ApiDtoEnvironment.Mechanus
    Environment.NineHells -> ApiDtoEnvironment.NineHells
    Environment.Shadowfell -> ApiDtoEnvironment.Shadowfell
    Environment.UpperPlanes -> ApiDtoEnvironment.UpperPlanes
    Environment.Swamp -> ApiDtoEnvironment.Swamp
    Environment.Underdark -> ApiDtoEnvironment.Underdark
    Environment.Underwater -> ApiDtoEnvironment.Underwater
    Environment.Urban -> ApiDtoEnvironment.Urban
}

fun Size.toDto(): ApiDtoSize = when (this) {
    Size.TINY -> ApiDtoSize.TINY
    Size.SMALL -> ApiDtoSize.SMALL
    Size.MEDIUM -> ApiDtoSize.MEDIUM
    Size.MEDIUM_OR_SMALL -> ApiDtoSize.MEDIUM
    Size.LARGE -> ApiDtoSize.LARGE
    Size.HUGE -> ApiDtoSize.HUGE
    Size.GARGANTUAN -> ApiDtoSize.GARGANTUAN
}

fun Alignment.toDto(): ApiDtoAlignment = when (this) {
    Alignment.LAWFUL_GOOD -> ApiDtoAlignment.LAWFUL_GOOD
    Alignment.NEUTRAL_GOOD -> ApiDtoAlignment.NEUTRAL_GOOD
    Alignment.CHAOTIC_GOOD -> ApiDtoAlignment.CHAOTIC_GOOD
    Alignment.LAWFUL_NEUTRAL -> ApiDtoAlignment.LAWFUL_NEUTRAL
    Alignment.NEUTRAL -> ApiDtoAlignment.NEUTRAL
    Alignment.CHAOTIC_NEUTRAL -> ApiDtoAlignment.CHAOTIC_NEUTRAL
    Alignment.LAWFUL_EVIL -> ApiDtoAlignment.LAWFUL_EVIL
    Alignment.NEUTRAL_EVIL -> ApiDtoAlignment.NEUTRAL_EVIL
    Alignment.CHAOTIC_EVIL -> ApiDtoAlignment.CHAOTIC_EVIL
}

fun Map<StatType, Long>.toDto() = StatDto(
    strength = getOrDefault(StatType.STR, 0),
    dexterity = getOrDefault(StatType.DEX, 0),
    constitution = getOrDefault(StatType.CON, 0),
    intelligence = getOrDefault(StatType.INT, 0),
    wisdom = getOrDefault(StatType.WIS, 0),
    charisma = getOrDefault(StatType.CHA, 0),
)

fun ApiDtoSize.toDomain(): Size = when (this) {
    ApiDtoSize.TINY -> Size.TINY
    ApiDtoSize.SMALL -> Size.SMALL
    ApiDtoSize.MEDIUM -> Size.MEDIUM
    ApiDtoSize.LARGE -> Size.LARGE
    ApiDtoSize.HUGE -> Size.HUGE
    ApiDtoSize.GARGANTUAN -> Size.GARGANTUAN
}

fun ApiDtoEnvironment.toDomain(): Environment = when (this) {
    ApiDtoEnvironment.Any -> Environment.Any
    ApiDtoEnvironment.Arctic -> Environment.Arctic
    ApiDtoEnvironment.Coastal -> Environment.Coastal
    ApiDtoEnvironment.Desert -> Environment.Desert
    ApiDtoEnvironment.Forest -> Environment.Forest
    ApiDtoEnvironment.Grassland -> Environment.Grassland
    ApiDtoEnvironment.Hill -> Environment.Hill
    ApiDtoEnvironment.Mountain -> Environment.Mountain
    ApiDtoEnvironment.Abyss -> Environment.Abyss
    ApiDtoEnvironment.Acheron -> Environment.Acheron
    ApiDtoEnvironment.AstralPlane -> Environment.AstralPlane
    ApiDtoEnvironment.Beastlands -> Environment.Beastlands
    ApiDtoEnvironment.ElementalChaos -> Environment.ElementalChaos
    ApiDtoEnvironment.ElementalAir -> Environment.ElementalAir
    ApiDtoEnvironment.ElementalEarth -> Environment.ElementalEarth
    ApiDtoEnvironment.ElementalFire -> Environment.ElementalFire
    ApiDtoEnvironment.ElementalWater -> Environment.ElementalWater
    ApiDtoEnvironment.ElementalPlanes -> Environment.ElementalPlanes
    ApiDtoEnvironment.EtherealPlane -> Environment.EtherealPlane
    ApiDtoEnvironment.Feywild -> Environment.Feywild
    ApiDtoEnvironment.Gehenna -> Environment.Gehenna
    ApiDtoEnvironment.Limbo -> Environment.Limbo
    ApiDtoEnvironment.LowerPlanes -> Environment.LowerPlanes
    ApiDtoEnvironment.Mechanus -> Environment.Mechanus
    ApiDtoEnvironment.NineHells -> Environment.NineHells
    ApiDtoEnvironment.Shadowfell -> Environment.Shadowfell
    ApiDtoEnvironment.UpperPlanes -> Environment.UpperPlanes
    ApiDtoEnvironment.Swamp -> Environment.Swamp
    ApiDtoEnvironment.Underdark -> Environment.Underdark
    ApiDtoEnvironment.Underwater -> Environment.Underwater
    ApiDtoEnvironment.Urban -> Environment.Urban
}

fun ApiDtoMonsterType.toDomain(): MonsterType = when (this) {
    ApiDtoMonsterType.ABERRATION -> MonsterType.ABERRATION
    ApiDtoMonsterType.BEAST -> MonsterType.BEAST
    ApiDtoMonsterType.CELESTIAL -> MonsterType.CELESTIAL
    ApiDtoMonsterType.CONSTRUCT -> MonsterType.CONSTRUCT
    ApiDtoMonsterType.DRAGON -> MonsterType.DRAGON
    ApiDtoMonsterType.ELEMENTAL -> MonsterType.ELEMENTAL
    ApiDtoMonsterType.FEY -> MonsterType.FEY
    ApiDtoMonsterType.FIEND -> MonsterType.FIEND
    ApiDtoMonsterType.GIANT -> MonsterType.GIANT
    ApiDtoMonsterType.HUMANOID -> MonsterType.HUMANOID
    ApiDtoMonsterType.MONSTROSITY -> MonsterType.MONSTROSITY
    ApiDtoMonsterType.OOZE -> MonsterType.OOZE
    ApiDtoMonsterType.PLANT -> MonsterType.PLANT
    ApiDtoMonsterType.UNDEAD -> MonsterType.UNDEAD
}

fun ApiDtoAlignment.toDomain(): Alignment = when (this) {
    ApiDtoAlignment.LAWFUL_GOOD -> Alignment.LAWFUL_GOOD
    ApiDtoAlignment.NEUTRAL_GOOD -> Alignment.NEUTRAL_GOOD
    ApiDtoAlignment.CHAOTIC_GOOD -> Alignment.CHAOTIC_GOOD
    ApiDtoAlignment.LAWFUL_NEUTRAL -> Alignment.LAWFUL_NEUTRAL
    ApiDtoAlignment.NEUTRAL -> Alignment.NEUTRAL
    ApiDtoAlignment.CHAOTIC_NEUTRAL -> Alignment.CHAOTIC_NEUTRAL
    ApiDtoAlignment.LAWFUL_EVIL -> Alignment.LAWFUL_EVIL
    ApiDtoAlignment.NEUTRAL_EVIL -> Alignment.NEUTRAL_EVIL
    ApiDtoAlignment.CHAOTIC_EVIL -> Alignment.CHAOTIC_EVIL
}

fun CreateMonsterDto.toMonster() = Monster(
    name = name,
    url = url,
    avatarUrl = avatarUrl,
    description = description,
    size = size.toDomain(),
    type = type.toDomain(),
    possibleAlignments = setOf(this.alignment.toDomain()),
    stats = stats.toDomain(),
    armorClass = armorClass.toInt(),
    challengeRating = challengeRatingId.toInt(),
    environments = environments.map { it.toDomain() }.toSet(),
    languages = languages.map { /*again too scary to touch without a string*/Language.valueOf(it) }.toSet(),
    externalId = null,
    source = Monster.Source.MANUAL,
)

fun StatDto.toDomain() = mapOf(
    StatType.STR to strength,
    StatType.DEX to dexterity,
    StatType.CON to constitution,
    StatType.INT to intelligence,
    StatType.WIS to wisdom,
    StatType.CHA to charisma,
)

