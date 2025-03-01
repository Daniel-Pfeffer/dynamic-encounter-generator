package at.jku.deq.service.dto

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.api.dto.Pageable

interface MonsterDtoService {
    fun getMonsterById(id: Long): MonsterDto
    fun getMonsters(pageable: Pageable): Page<MonsterDto>
    fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto
    fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto
    fun deleteMonster(id: Long)
}