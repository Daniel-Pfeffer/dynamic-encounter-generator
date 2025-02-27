package at.jku.deq.domain.repository

import at.jku.deq.domain.entity.Monster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface MonsterRepository : JpaRepository<Monster, Long> {

    @Query("SELECT m FROM Monster m WHERE m.externalId IN :ids")
    fun findAllByExternalId(ids: Set<Long>): List<Monster>

    @Query("DELETE FROM Monster m WHERE m.externalId IN :ids")
    @Modifying
    fun deleteAllByExternalId(ids: Set<Long>)
}
