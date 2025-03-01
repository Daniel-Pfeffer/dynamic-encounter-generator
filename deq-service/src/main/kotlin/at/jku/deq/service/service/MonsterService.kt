package at.jku.deq.service.service

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.api.dto.MonsterDto
import at.jku.deq.api.dto.Page
import at.jku.deq.domain.repository.MonsterRepository
import at.jku.deq.service.mapper.toDto
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
internal class MonsterService(
    private val monsterRepository: MonsterRepository
) {

    companion object {
        private val LOG = KotlinLogging.logger {}

        private val DEFAULT_SORT = Sort.by(Sort.Order.desc("id"))
    }

    fun getMonsters(): Page<MonsterDto> {
        return monsterRepository.findAll(Pageable.unpaged(DEFAULT_SORT)).toCommonsPage {
            it.toDto()
        }
    }

    fun <T, X> org.springframework.data.domain.Page<T>.toCommonsPage(contentMapper: (T) -> X): Page<X> {
        return Page.of(content.map(contentMapper), number.toLong(), totalElements)
    }


    fun getMonsterById(id: Long): MonsterDto {
        val monster = monsterRepository.getReferenceById(id)

        return monster.toDto()
    }

    fun createMonster(monster: CreateMonsterDto): MonsterDto {
        TODO()
    }

    fun updateMonster(id: Long, monster: CreateMonsterDto): MonsterDto {
        val monster = monsterRepository.getReferenceById(id)

        return monster.toDto()
    }

    fun deleteMonster(id: Long) {
        monsterRepository.deleteById(id)
    }
}