package at.jku.deq.ddbeyond.api.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class DndBeyondMonsterApiResult(
    val accessType: Map<String, Int>,
    val pagination: Pagination,
    val stats: Stats,
    val metaData: MetaData,
    val data: List<DndBeyondMonster>
)

@Serializable
internal data class Pagination(
    val take: Long,
    val skip: Long,
    val total: Long,
)

@Serializable
internal data class Stats(
    val elapsedMilliseconds: Long,
)

@Serializable
internal data class MetaData(
    val maxScore: Double,
    val itemScores: List<ItemScore>,
)

@Serializable
internal data class ItemScore(
    val id: String,
    val score: Double,
)

@Serializable
internal data class DndBeyondMonster(
    val collectionUserId: Long,
    val isReleased: Boolean,
    val url: String,
    val conditionImmunitiesHtml: String,
    val sensesHtml: String,
    val skillsHtml: String,
    val stats: List<Stat>,
    val senses: List<Sense>,
    val savingThrows: List<SavingThrow>,
    val skills: List<Skill>,
    val languages: List<Language>,
    val hitPointDice: HitPointDice,
    val swarm: Swarm?,
    val movements: List<Movement>,
    val homebrewStatus: Long,
    val id: Long,
    val entityTypeId: Long,
    val name: String,
    val alignmentId: Long,
    val sizeId: Long,
    val typeId: Long,
    val armorClass: Long,
    val armorClassDescription: String,
    val averageHitPoints: Long,
    val passivePerception: Long,
    val isHomebrew: Boolean,
    val challengeRatingId: Long,
    val sourceId: Long,
    val sourcePageNumber: Long?,
    val isLegendary: Boolean,
    val isMythic: Boolean,
    val hasLair: Boolean,
    val avatarUrl: String,
    val largeAvatarUrl: String?,
    val basicAvatarUrl: String?,
    val version: String?,
    val subTypes: List<Long>,
    val environments: List<Long>,
    val tags: List<String>,
    val sources: List<Source>,
    val damageAdjustments: List<Long>,
    val conditionImmunities: List<Long>,
    val specialTraitsDescription: String,
    val actionsDescription: String,
    val reactionsDescription: String,
    val legendaryActionsDescription: String,
    val mythicActionsDescription: String?,
    val bonusActionsDescription: String?,
    val characteristicsDescription: String,
    val lairDescription: String,
    val languageDescription: String?,
    val languageNote: String,
    val hideCr: Boolean,
    val isLegacy: Boolean,
)

@Serializable
internal data class Sense(
    val senseId: Long,
    val notes: String
)

@Serializable
internal data class Stat(
    val statId: Long,
    val value: Long,
)

@Serializable
internal data class SavingThrow(
    val statId: Long,
)

@Serializable
internal data class Skill(
    val skillId: Long,
    val value: Long,
)

@Serializable
internal data class Language(
    val languageId: Long,
    val notes: String,
)

@Serializable
internal data class HitPointDice(
    val diceCount: Long,
    val diceValue: Long,
    val diceMultiplier: Long,
    val fixedValue: Long,
    val diceString: String,
)

@Serializable
internal data class Swarm(
    val name: String,
    val sizeId: Long,
    val typeId: Long,
)

@Serializable
internal data class Movement(
    val movementId: Long,
    val speed: Long,
    val notes: String?,
)
@Serializable
internal data class Source(
    val sourceId: Long,
    val pageNumber: Long?,
    val sourceType: Long,
)