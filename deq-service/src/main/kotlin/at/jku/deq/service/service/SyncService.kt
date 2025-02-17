package at.jku.deq.service.service

import at.jku.deq.ddbeyond.service.MonsterService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
internal class SyncService(
    private val monsterservice: MonsterService
) {

    companion object {
        private val LOG = KotlinLogging.logger {}
    }

    fun sync() {
        val allMonster = monsterservice.getAllMonsters()
        LOG.info { allMonster }
    }
}