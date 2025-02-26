package at.jku.deq.domain.repository

import at.jku.deq.domain.entity.Monster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MonsterRepository : JpaRepository<Monster, Long> {

    @Query("SELECT m FROM Monster m WHERE m.externalId IN :ids")
    fun findAllByExternalId(ids: Set<Long>): List<Monster>
}