package at.jku.deq.domain.entity

import jakarta.persistence.*

@Entity
class Monster(
    @Column(unique = true)
    val externalId: Long,
    val name: String,
    val url: String,
    val avatarUrl: String,
    val description: String,
    val size: Size,
    val type: MonsterType,
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    val possibleAlignments: Set<Alignment>,
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    val stats: Map<StatType, Long>,
    val armorClass: Int,
    val challengeRating: Int,
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    val environments: Set<Environment>,
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    val languages: Set<Language>,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
}

enum class MonsterType {
    ABERRATION,
    BEAST,
    CELESTIAL,
    CONSTRUCT,
    DRAGON,
    ELEMENTAL,
    FEY,
    FIEND,
    GIANT,
    HUMANOID,
    MONSTROSITY,
    OOZE,
    PLANT,
    UNDEAD
}

enum class Size {
    TINY,
    SMALL,
    MEDIUM,
    MEDIUM_OR_SMALL,
    LARGE,
    HUGE,
    GARGANTUAN
}

enum class Alignment {
    LAWFUL_GOOD,
    NEUTRAL_GOOD,
    CHAOTIC_GOOD,
    LAWFUL_NEUTRAL,
    NEUTRAL,
    CHAOTIC_NEUTRAL,
    LAWFUL_EVIL,
    NEUTRAL_EVIL,
    CHAOTIC_EVIL
}

enum class Environment {
    Any, Arctic, Coastal,
    Desert, Forest, Grassland,
    Hill, Mountain, Abyss,
    Acheron, AstralPlane,
    Beastlands, ElementalChaos,
    ElementalAir, ElementalEarth,
    ElementalFire, ElementalWater,
    ElementalPlanes, EtherealPlane,
    Feywild, Gehenna, Limbo,
    LowerPlanes, Mechanus, NineHells,
    Shadowfell, UpperPlanes, Swamp,
    Underdark, Underwater, Urban;
}

enum class StatType {
    STR, DEX, CON, INT, WIS, CHA
}

enum class SenseType {
    Blindsight, Darkvision, TremorSense, Truesight
}

enum class Skill {
    Athletics,
    Acrobatics,
    SleightOfHand,
    Stealth,
    Arcana,
    History,
    Investigation,
    Nature,
    Religion,
    AnimalHandling,
    Insight,
    Medicine,
    Perception,
    Survival,
    Deception,
    Intimidation,
    Performance,
    Persuasion,
}