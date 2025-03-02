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
    val size: String,
    val alignment: String,
    val stats: StatDto,
    val armorClass: Int,
    val challengeRatingId: Int,
    val xp: Long,
    val environments: Set<String>,
    val languages: Set<String>
)


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
    val size: String,
    val alignment: String,
    val type: String,
    val stats: StatDto,
    val armorClass: Long,
    val challengeRatingId: Long,
    val environments: Set<String>,
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