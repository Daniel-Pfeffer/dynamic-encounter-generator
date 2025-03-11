package at.jku.deq.service.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "deq.xp")
data class XpConfig(
    val xpPerCrId: Map<Int, Int>,
    val encounterDifficulty: List<EncounterDifficulty>
) {
    fun getXp(cr: Int): Int {
        assert(cr in xpPerCrId) { "CR $cr not found in xpPerCrId" }
        return xpPerCrId.getValue(cr)
    }
}

data class EncounterDifficulty(
    val partyLevel: Long,
    val difficulty: Map<Difficulty, Long>
)

enum class Difficulty {
    Low,
    Moderate,
    High,
    None,
}