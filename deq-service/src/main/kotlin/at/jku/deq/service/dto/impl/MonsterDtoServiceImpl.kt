package at.jku.deq.service.dto.impl

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.service.config.XpConfig
import at.jku.deq.service.dto.MonsterDtoService
import at.jku.deq.service.mapper.toCommonsPage
import at.jku.deq.service.mapper.toDto
import at.jku.deq.service.service.MonsterService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
internal class MonsterDtoServiceImpl(
    private val monsterService: MonsterService,
    private val xpConfig: XpConfig
) : MonsterDtoService {
    override fun getMonsterById(id: Long): MonsterDto {
        return monsterService.getMonsterById(id)
            .toDto(xpConfig.xpPerCrId)
    }

    override fun getMonsters(pageable: Pageable): Page<MonsterDto> {
        return monsterService.getMonsters(pageable).toCommonsPage {
            it.toDto(xpConfig.xpPerCrId)
        }
    }

    override fun createOrUpdateMonster(monster: CreateMonsterDto): MonsterDto {
        return if (monster.id == null) {
            monsterService.createMonster(monster)
        } else {
            monsterService.updateMonster(monster.id!!, monster)
        }.toDto(xpConfig.xpPerCrId)
    }

    override fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto {
        return monsterService.updateMonster(id, monster)
            .toDto(xpConfig.xpPerCrId)
    }

    override fun deleteMonster(id: Long) {
        monsterService.deleteMonster(id)
    }
}