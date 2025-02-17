package at.jku.deq.ddbeyond.dto

data class DeqMonster(
    val id: Long,
    val name: String,
    val url: String,
    val description: String,
    val sizeId: Long,
    val typeId: Long,
    val alignment: Alignment,
    val stats: Map<StatType, Int>,
    val armorClass: Long,
    val challengeRatingId: Long,
)

enum class Alignment(private val id: Long /*just for cross-checking*/) {
    LawfulGood(1),
    NeutralGood(2),
    ChaoticGood(3),
    LawfulNeutral(4),
    Neutral(5),
    ChaoticNeutral(6),
    LawfulEvil(7),
    NeutralEvil(8),
    ChaoticEvil(9),
    Unaligned(10);

    companion object {
        fun fromId(id: Long): Alignment {
            return entries[id.toInt() - 1]
        }
    }
}

enum class StatType(private val id: Long) {
    Strength(1),
    Dexterity(2),
    Constitution(3),
    Intelligence(4),
    Wisdom(5),
    Charisma(6);
}

enum class SenseType(private val id: Long) {
    Blindsight(1),
    Darkvision(2),
    Tremorsense(3),
    Truesight(4)
}

enum class Skill(private val id: Long){
    // why no 1????????
    // ordered by StatType and alphabetical?
    Athletics(2),
    Acrobatics(3),
    SleightOfHand(4)/*?*/,
    Stealth(5),
    Arcana(6),
    History(7),
    Investigation(8)/*?*/,
    Nature(9),
    Religion(10),
    AnimalHandling(11)/*?*/,
    Insight(12),
    Medicine(13),
    Perception(14),
    Survival(15)/*?*/,
    Deception(16)/*?*/,
    Intimidation(17)/*?*/,
    Performance(18)/*?*/,
    Persuasion(19)/*?*/,
}