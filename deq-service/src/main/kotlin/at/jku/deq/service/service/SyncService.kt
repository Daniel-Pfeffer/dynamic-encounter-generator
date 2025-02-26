package at.jku.deq.service.service

import at.jku.deq.ddbeyond.service.MonsterService
import at.jku.deq.domain.repository.MonsterRepository
import at.jku.deq.service.mapper.toDomainMonster
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
internal class SyncService(
    private val monsterservice: MonsterService,
    private val monsterRepository: MonsterRepository
) {

    companion object {
        private val LOG = KotlinLogging.logger {}
    }

    fun sync() {
        val allMonster = monsterservice.getAllMonsters()
            .map { it.toDomainMonster() }


        val existingMonster = monsterRepository.findAll()


        monsterRepository.saveAll(allMonster)
    }
}