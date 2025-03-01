package at.jku.deq.service.dto

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.api.dto.Pageable
import at.jku.deq.service.service.MonsterService
import org.springframework.stereotype.Service

@Service
internal class MonsterDtoServiceImpl(
    private val monsterService: MonsterService
) : MonsterDtoService {
    override fun getMonsterById(id: Long): MonsterDto {
        return monsterService.getMonsterById(id)
    }

    override fun getMonsters(pageable: Pageable): Page<MonsterDto> {
        TODO("Not yet implemented")
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