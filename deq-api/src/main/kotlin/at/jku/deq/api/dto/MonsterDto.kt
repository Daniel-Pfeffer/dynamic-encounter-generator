package at.jku.deq.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.Serializable

@Serializable
data class MonsterDto(
    val id: Long,
    val name: String,
    val url: String?,
    val avatarUrl: String?,
    val description: String,
    val size: Size,
    val type: MonsterType,
    val alignments: Set<Alignment>,
    val stats: StatDto,
    val armorClass: Int,
    val challengeRatingId: Int,
    val xp: Long,
    val environments: Set<Environment>,
    val languages: Set<String>/*scary*/
)

@Serializable
data class MonsterFilter(
    val name: String? = null,
    val size: Size? = null,
    val type: MonsterType? = null,
    val environments: Set<Environment>? = null,
    val languages: Set<String>? = null,
    val challengeRating: Long? = null,
    val alignment: Alignment? = null,
)

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


@Schema(name = "CreateMonsterDto", description = "The DTO for creating or updating a monster")
@Serializable
data class CreateMonsterDto(
    @Schema(
        description = "The id of the monster, if null a new monster will be created otherwise the given",
        example = "0",
        nullable = true
    )
    val id: Long? = null,
    val name: String,
    val url: String? = null,
    val avatarUrl: String? = null,
    val description: String,
    val size: Size,
    val alignment: Alignment,
    val type: MonsterType,
    val stats: StatDto,
    val armorClass: Long,
    val challengeRatingId: Long,
    val environments: Set<Environment>,
    val languages: Set<String>
)

@Serializable
data class StatDto(
    val strength: Long,
    val dexterity: Long,
    val constitution: Long,
    val intelligence: Long,
    val wisdom: Long,
    val charisma: Long
)