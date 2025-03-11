package at.jku.deq.domain.service

import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.model.MonsterFilter

interface MonsterRecommendationService {
    fun findByExample(filter: MonsterFilter): List<Monster>
}