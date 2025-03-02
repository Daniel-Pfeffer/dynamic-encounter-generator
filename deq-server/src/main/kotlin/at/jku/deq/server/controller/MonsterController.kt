package at.jku.deq.server.controller

import at.jku.deq.api.MonsterApi
import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.service.dto.MonsterDtoService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
internal class MonsterController(
    private val monsterDtoService: MonsterDtoService
) : MonsterApi {
    override fun getMonsterById(id: Long): MonsterDto {
        return monsterDtoService.getMonsterById(id)
    }

    override fun getMonsters(page: Int, size: Int): Page<MonsterDto> {
        val pageRequest = if (size == 0) Pageable.unpaged() else PageRequest.of(page, size)
        return monsterDtoService.getMonsters(pageRequest)
    }

    override fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto {
        return monsterDtoService.createOrUpdateMonster(monster)
    }

    override fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto {
        return monsterDtoService.updateMonster(id, monster)
    }

    override fun deleteMonster(id: Long) {
        monsterDtoService.deleteMonster(id)
    }
}