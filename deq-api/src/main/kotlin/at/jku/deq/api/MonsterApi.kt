package at.jku.deq.api

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import org.springframework.web.bind.annotation.*

interface MonsterApi {
    @GetMapping("monsters/{id}")
    fun getMonsterById(@PathVariable id: Long): MonsterDto

    @GetMapping("monsters")
    fun getMonsters(
        @RequestParam(required = false) page: Int = 0,
        @RequestParam(required = false) size: Int = 0
    ): Page<MonsterDto>

    @PostMapping("monsters")
    fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto

    @PostMapping("monsters/{id}")
    fun updateMonster(@PathVariable id: Long, monster: CreateMonsterDto): MonsterDto

    @DeleteMapping("monsters/{id}")
    fun deleteMonster(@PathVariable id: Long)
}