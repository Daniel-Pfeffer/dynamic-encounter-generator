package at.jku.deq.service.dto

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.service.service.MonsterService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
internal class MonsterDtoServiceImpl(
    private val monsterService: MonsterService
) : MonsterDtoService {
    override fun getMonsterById(id: Long): MonsterDto {
        return monsterService.getMonsterById(id)
    }

    override fun getMonsters(pageable: Pageable): Page<MonsterDto> {
        return monsterService.getMonsters(pageable)
    }

    override fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto {
        return if (monster.id == null) {
            monsterService.createMonster(monster)
        } else {
            monsterService.updateMonster(monster.id!!, monster)
        }
    }

    override fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto {
        return monsterService.updateMonster(id, monster)
    }

    override fun deleteMonster(id: Long) {
        monsterService.deleteMonster(id)
    }
}