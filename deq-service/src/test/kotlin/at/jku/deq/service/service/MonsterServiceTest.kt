package at.jku.deq.service.service

import at.jku.deq.api.dto.CreateMonsterDtoFactory
import at.jku.deq.api.dto.MonsterDtoFactory
import at.jku.deq.domain.MonsterFactory
import at.jku.deq.domain.entity.Monster
import at.jku.deq.domain.repository.MonsterRepository
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MonsterServiceTest {

    private val monsterRepository = mockk<MonsterRepository> {
        every {
            save(any())
        } returnsArgument 0
        every { delete(any()) } just runs
    }
    private val monsterService = MonsterService(monsterRepository)

    private val monsterDtoFactory = MonsterDtoFactory()
    private val monsterFactory = MonsterFactory()
    private val createMonsterFactory = CreateMonsterDtoFactory()


    @AfterEach
    fun afterEach() {
        confirmVerified(monsterRepository)
    }

    @Nested
    inner class GetMonsterById {
        @Test
        fun `should return monster by id`() {
            // Given
            val id = 1L
            every { monsterRepository.getReferenceById(id) } returns monsterFactory.create()

            // When
            val result = monsterService.getMonsterById(id)

            // Then
            assertThat(result)
                .usingRecursiveComparison()
                .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                .isEqualTo(monsterFactory.create())

            verify(exactly = 1) { monsterRepository.getReferenceById(id) }
        }
    }

    @Nested
    inner class UpdateMonster {
        @Test
        fun `should update the monster`() {
            // Given
            val id = 1L
            val monster = createMonsterFactory.create(
                name = "newName",
                description = "a description"
            )
            every { monsterRepository.getReferenceById(id) } returns monsterFactory.create(
                name = "oldName",
                description = "a description"
            )

            // When
            val result = monsterService.updateMonster(id, monster)

            // Then
            val expectedMonster = monsterFactory.create(name = "newName", description = "a description")
            assertThat(result)
                .usingRecursiveComparison()
                .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                .isEqualTo(expectedMonster)

            verify(exactly = 1) {
                monsterRepository.getReferenceById(id)
                monsterRepository.save(withArg {
                    assertThat(it)
                        .usingRecursiveComparison()
                        .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                        .isEqualTo(expectedMonster)
                })
            }
        }

        @Test
        fun `cannot update with external source`() {
            // Given
            val id = 1L
            val monster = createMonsterFactory.create()
            every { monsterRepository.getReferenceById(id) } returns monsterFactory.create(
                source = Monster.Source.EXTERNAL
            )

            // When
            val exception = assertThrows<IllegalArgumentException> {
                monsterService.updateMonster(id, monster)
            }

            // Then
            assertThat(exception.message).isEqualTo("Can't update external monster")
            verify(exactly = 1) {
                monsterRepository.getReferenceById(id)
            }
        }
    }

    @Nested
    inner class DeleteMonster {
        @Test
        fun `should delete the monster`() {
            // Given
            val id = 1L
            every { monsterRepository.getReferenceById(id) } returns monsterFactory.create()

            // When
            monsterService.deleteMonster(id)

            // Then
            verify(exactly = 1) {
                monsterRepository.getReferenceById(id)
                monsterRepository.delete(withArg {
                    assertThat(it)
                        .usingRecursiveComparison()
                        .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                        .isEqualTo(monsterFactory.create())
                })
            }
        }

        @Test
        fun `cannot delete with external source`() {
            // Given
            val id = 1L
            every { monsterRepository.getReferenceById(id) } returns monsterFactory.create(
                source = Monster.Source.EXTERNAL
            )

            // When
            val exception = assertThrows<IllegalArgumentException> {
                monsterService.deleteMonster(id)
            }

            // Then
            assertThat(exception.message).isEqualTo("Can't delete external monster")
            verify(exactly = 1) {
                monsterRepository.getReferenceById(id)
            }
        }
    }

    @Nested
    inner class CreateMonster {
        @Test
        fun `should create a monster`() {
            // Given
            val monster = createMonsterFactory.create(
                name = "name",
                description = "a description"
            )
            every { monsterRepository.save(any()) } returnsArgument 0

            // When
            val result = monsterService.createMonster(monster)

            // Then
            val expected = monsterFactory.create(
                name = "name",
                description = "a description"
            )
            assertThat(result)
                .usingRecursiveComparison()
                .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                .isEqualTo(expected)

            verify(exactly = 1) {
                monsterRepository.save(withArg {
                    assertThat(it)
                        .usingRecursiveComparison()
                        .comparingOnlyFields(Monster::id.name, Monster::name.name, Monster::description.name)
                        .isEqualTo(expected)
                })
            }
        }
    }
}