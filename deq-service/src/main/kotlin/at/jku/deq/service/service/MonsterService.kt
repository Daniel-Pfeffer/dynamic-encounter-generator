package at.jku.deq.service.service

import at.jku.deq.api.dto.CreateMonsterDto
import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.repository.MonsterRepository
import at.jku.deq.service.mapper.toMonster
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class MonsterService(
    private val monsterRepository: MonsterRepository
) {

    companion object {
        private val LOG = KotlinLogging.logger {}
    }

    @Transactional(readOnly = true)
    fun getMonsters(pageable: Pageable): Page<Monster> {
        LOG.trace { "Called getMonsters with $pageable" }
        return monsterRepository.findAll(pageable)
    }

    @Transactional(readOnly = true)
    fun getMonsterById(id: Long): Monster {
        LOG.trace { "Called getMonsterById with $id" }
        val monster = monsterRepository.getReferenceById(id)

        return monster
    }

    @Transactional
    fun createMonster(monster: CreateMonsterDto): Monster {
        LOG.trace { "Called createMonster for ${monster.name}" }
        val newMonster = monsterRepository.save(monster.toMonster())

        return newMonster
    }

    @Transactional
    fun updateMonster(id: Long, monster: CreateMonsterDto): Monster {
        LOG.trace { "Called updateMonster for $id" }
        val dbMonster = monsterRepository.getReferenceById(id)

        // if monster is external, we can't update it
        if (dbMonster.source == Monster.Source.EXTERNAL)
            throw IllegalArgumentException("Can't update external monster")

        val newMonster = monster.toMonster()

        val reallyNewMonster = monsterRepository.save(Monster.fromMonster(dbMonster, newMonster))

        return reallyNewMonster
    }

    @Transactional
    fun deleteMonster(id: Long) {
        LOG.trace { "Called deleteMonster for $id" }
        val monster = monsterRepository.getReferenceById(id)
        if (monster.source == Monster.Source.EXTERNAL)
            throw IllegalArgumentException("Can't delete external monster")

        monsterRepository.delete(monster)
    }
}