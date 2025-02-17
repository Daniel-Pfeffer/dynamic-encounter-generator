package at.jku.deq.ddbeyond.service

import at.jku.deq.ddbeyond.dto.DeqMonster

interface MonsterService {
    fun getAllMonsters(): List<DeqMonster>
}