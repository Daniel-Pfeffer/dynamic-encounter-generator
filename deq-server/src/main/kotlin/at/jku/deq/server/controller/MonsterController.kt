package at.jku.deq.server.controller

import at.jku.deq.api.MonsterApi
import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.api.dto.Pageable
import at.jku.deq.service.dto.MonsterDtoService
import org.springframework.web.bind.annotation.RestController

@RestController
internal class MonsterController (
    private val monsterDtoService: MonsterDtoService
): MonsterApi{
    override fun getMonsterById(id: Long): MonsterDto {
        return monsterDtoService.getMonsterById(id)
    }

    override fun getMonsters(pageable: Pageable): Page<MonsterDto> {
        return monsterDtoService.getMonsters()
    }

    override fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto {
        TODO("Not yet implemented")
    }

    override fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto {
        TODO("Not yet implemented")
    }

    override fun deleteMonster(id: Long) {
        TODO("Not yet implemented")
    }
}