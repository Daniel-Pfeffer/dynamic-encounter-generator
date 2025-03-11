package at.jku.deq.service.service

import at.jku.deq.ddbeyond.dto.DeqMonsterFactory
import at.jku.deq.ddbeyond.service.MonsterService
import at.jku.deq.domain.MonsterFactory
import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.repository.MonsterRepository
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SyncServiceTest {

    private val monsterService = mockk<MonsterService>()
    private val monsterRepository = mockk<MonsterRepository> {
        every {
            saveAll<Monster>(any())
        } returnsArgument 0
    }

    private val syncService = SyncService(monsterService, monsterRepository)

    companion object {
        private val FACTORY = MonsterFactory()
        private val DEQ_FACTORY = DeqMonsterFactory()
    }

    @AfterEach
    fun confirmEverythingVerified() {
        confirmVerified(monsterService, monsterRepository)
    }

    @Nested
    inner class SyncTest {
        @Test
        fun `test basic sync`() {
            // given
            val retrievedMonsters =
                listOf(DEQ_FACTORY.create(name = "Big Bad Voodoo", id = 1), DEQ_FACTORY.create(name = "Rexxar", id = 2))
            every { monsterService.getAllMonsters() } returns retrievedMonsters
            every { monsterRepository.findAll() } returns emptyList()
            every { monsterRepository.deleteAll(emptyList()) } just runs

            // when
            syncService.sync()

            // then
            verify(exactly = 1) {
                monsterService.getAllMonsters()
                monsterRepository.findAll()
                monsterRepository.deleteAll(emptyList())
                monsterRepository.saveAll(withArg<List<Monster>> {
                    assertThat(it)
                        .hasSize(2)
                        .usingRecursiveFieldByFieldElementComparatorOnFields(Monster::id.name, Monster::externalId.name)
                        .containsExactly(
                            FACTORY.create(name = "Big Bad Voodoo", externalId = 1),
                            FACTORY.create(name = "Rexxar", externalId = 2)
                        )
                })
            }
        }

        @Test
        fun `test sync - delete monster not existing anymore`() {
            // given
            every { monsterService.getAllMonsters() } returns emptyList()
            every { monsterRepository.findAll() } returns listOf(FACTORY.create(externalId = 3L, name = "Old Monster"))
            every { monsterRepository.deleteAll(any()) } just runs

            // when
            syncService.sync()

            // then
            verify(exactly = 1) {
                monsterService.getAllMonsters()
                monsterRepository.findAll()
                monsterRepository.saveAll(emptyList())
                monsterRepository.deleteAll(withArg<List<Monster>> {
                    assertThat(it).hasSize(1)
                        .usingRecursiveFieldByFieldElementComparatorOnFields(Monster::id.name, Monster::externalId.name)
                        .containsExactly(FACTORY.create(externalId = 3L, name = "Old Monster"))
                })
            }
        }

        @Test
        fun `test sync - save and delete`() {
            // given
            val retrievedMonsters = listOf(
                DEQ_FACTORY.create(id = 1L, name = "Big Bad Voodoo"),
                DEQ_FACTORY.create(id = 2L, name = "Rexxar")
            )
            every { monsterService.getAllMonsters() } returns retrievedMonsters
            every { monsterRepository.findAll() } returns listOf(FACTORY.create(externalId = 3L, name = "Old Monster"))

            every { monsterRepository.deleteAll(any()) } just runs

            // when
            syncService.sync()

            // then
            verify(exactly = 1) {
                monsterService.getAllMonsters()
                monsterRepository.findAll()
                monsterRepository.saveAll(withArg<List<Monster>> {
                    assertThat(it)
                        .hasSize(2)
                        .usingRecursiveFieldByFieldElementComparatorOnFields(Monster::id.name, Monster::externalId.name)
                        .containsExactly(
                            FACTORY.create(externalId = 1L, name = "Big Bad Voodoo"),
                            FACTORY.create(externalId = 2L, name = "Rexxar")
                        )
                })
                monsterRepository.deleteAll(withArg<List<Monster>> {
                    assertThat(it).hasSize(1)
                        .usingRecursiveFieldByFieldElementComparatorOnFields(Monster::id.name, Monster::externalId.name)
                        .containsExactly(FACTORY.create(externalId = 3L, name = "Old Monster"))
                })
            }
        }

        @Test
        fun `test sync - overwrite existing monster`() {
            // given
            val retrievedMonsters = listOf(
                DEQ_FACTORY.create(id = 1L, name = "Big Bad Voodoo"),
            )
            every { monsterService.getAllMonsters() } returns retrievedMonsters
            every { monsterRepository.findAll() } returns listOf(
                FACTORY.create(
                    externalId = 1L,
                    name = "Small Good Voodoo"
                )
            )

            every { monsterRepository.deleteAll(any()) } just runs

            // when
            syncService.sync()

            // then
            verify(exactly = 1) {
                monsterService.getAllMonsters()
                monsterRepository.findAll()
                monsterRepository.saveAll(withArg<List<Monster>> {
                    assertThat(it)
                        .hasSize(1)
                        .usingRecursiveFieldByFieldElementComparatorOnFields(Monster::id.name, Monster::externalId.name)
                        .containsExactly(
                            FACTORY.create(externalId = 1L, name = "Small Good Voodoo"),
                        )
                })
                monsterRepository.deleteAll(emptyList())
            }
        }
    }
}