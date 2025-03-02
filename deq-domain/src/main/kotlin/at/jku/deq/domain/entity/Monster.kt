package at.jku.deq.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcType
import org.hibernate.dialect.PostgreSQLEnumJdbcType

@Entity
class Monster(
    @Column(unique = true, nullable = true)
    val externalId: Long?,
    val name: String,
    @Column(nullable = true)
    val url: String?,
    @Column(nullable = true)
    val avatarUrl: String?,
    val description: String,
    val size: Size,
    val type: MonsterType,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    val possibleAlignments: Set<Alignment>,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    val stats: Map<StatType, Long>,
    val armorClass: Int,
    val challengeRating: Int,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    val environments: Set<Environment>,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    val languages: Set<Language>,
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType::class)
    val source: Source,
) {

    enum class Source {
        EXTERNAL, MANUAL
    }

    companion object {
        fun fromMonster(original: Monster, updated: Monster): Monster {
            return Monster(
                updated.externalId,
                updated.name,
                updated.url,
                updated.avatarUrl,
                updated.description,
                updated.size,
                updated.type,
                updated.possibleAlignments,
                updated.stats,
                updated.armorClass,
                updated.challengeRating,
                updated.environments,
                updated.languages,
                updated.source,
            ).apply {
                this.id = original.id
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
        private set
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