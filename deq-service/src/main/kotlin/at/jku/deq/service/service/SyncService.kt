package at.jku.deq.service.service

import at.jku.deq.ddbeyond.service.MonsterService
import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.repository.MonsterRepository
import at.jku.deq.service.mapper.toDomainMonster
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
internal class SyncService(
    private val monsterservice: MonsterService,
    private val monsterRepository: MonsterRepository
) {

    companion object {
        private val LOG = KotlinLogging.logger {}
    }

    @Transactional
    fun sync() {
        val allDndBeyondMonster = monsterservice.getAllMonsters()

        val existingMonsters = monsterRepository.findAll()
        val existingExternalIds = existingMonsters.map { it.externalId }.toSet()
        val existingMonstersByExternalId = existingMonsters.associateBy { it.externalId }

        val toRemoveId = allDndBeyondMonster.filter { it.id !in existingExternalIds }.mapTo(HashSet()) { it.id }

        val toSave = allDndBeyondMonster.map {
            val existingMonster = existingMonstersByExternalId[it.id]
            if (existingMonster != null) {
                Monster.fromMonster(existingMonster, it.toDomainMonster())
            } else it.toDomainMonster()
        }
        LOG.info { "Removed ${toRemoveId.size} monsters" }
        LOG.info { "Saved ${toSave.size} new or updated monsters" }
        monsterRepository.saveAll(toSave)
        monsterRepository.deleteAllByExternalId(toRemoveId)
    }
}