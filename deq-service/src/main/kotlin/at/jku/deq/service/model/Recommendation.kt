package at.jku.deq.service.model

import at.jku.deq.domain.entity.Monster
import at.jku.deq.service.config.Difficulty

internal data class Recommendation(
    val monsters: List<Monster>,
    val xp: Long,
    val difficulty: Difficulty
)