package at.jku.deq.domain.service

import at.jku.deq.domain.DomainModule
import at.jku.deq.domain.MonsterFactory
import at.jku.deq.domain.entity.*
import at.jku.deq.domain.model.MonsterFilter
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@Import(DomainModule::class)
@Transactional
@Rollback
class MonsterRecommendationServiceIntegrationTest(
    @Autowired private val entityManager: EntityManager,
    @Autowired private val monsterRecommendationService: MonsterRecommendationService,
) {

    private val monsterFactory = MonsterFactory(entityManager::merge)

    private lateinit var orc: Monster
    private lateinit var legolas: Monster
    private lateinit var gimli: Monster
    private lateinit var aragorn: Monster
    private lateinit var gandalf: Monster
    private lateinit var frodo: Monster
    private lateinit var sam: Monster
    private lateinit var merry: Monster
    private lateinit var pippin: Monster
    private lateinit var sauron: Monster

    @BeforeEach
    fun setup() {
        orc = monsterFactory.create(
            name = "Orc",
            size = Size.LARGE,
            type = MonsterType.ABERRATION,
            challengeRating = 1,
            possibleAlignments = setOf(Alignment.CHAOTIC_EVIL),
            languages = setOf(Language.orc)
        )
        legolas = monsterFactory.create(
            name = "Legolas",
            size = Size.MEDIUM,
            type = MonsterType.HUMANOID,
            challengeRating = 1,
            possibleAlignments = setOf(Alignment.CHAOTIC_GOOD),
            languages = setOf(Language.elvish, Language.common)
        )
        gimli = monsterFactory.create(
            name = "Gimli",
            size = Size.MEDIUM,
            type = MonsterType.HUMANOID,
            challengeRating = 1,
            possibleAlignments = setOf(Alignment.LAWFUL_GOOD),
            languages = setOf(Language.common, Language.dwarvish)
        )

        aragorn = monsterFactory.create(
            name = "Aragorn",
            size = Size.MEDIUM,
            type = MonsterType.HUMANOID,
            challengeRating = 1,
            possibleAlignments = setOf(Alignment.LAWFUL_GOOD),
            languages = setOf(Language.common, Language.elvish)
        )
        gandalf = monsterFactory.create(
            name = "Gandalf",
            size = Size.MEDIUM,
            type = MonsterType.CELESTIAL,
            challengeRating = 2,
            possibleAlignments = setOf(Alignment.LAWFUL_GOOD),
            languages = setOf(Language.common, Language.elvish, Language.dwarvish, Language.celestial)
        )
        frodo = monsterFactory.create(
            name = "Frodo",
            size = Size.SMALL,
            type = MonsterType.HUMANOID,
            challengeRating = 2,
            possibleAlignments = setOf(Alignment.LAWFUL_GOOD),
            languages = setOf(Language.common, Language.elvish, Language.halfling)
        )
        sam = monsterFactory.create(
            name = "Sam",
            size = Size.SMALL,
            type = MonsterType.HUMANOID,
            challengeRating = 2,
            possibleAlignments = setOf(Alignment.LAWFUL_GOOD),
            languages = setOf(Language.common, Language.halfling)
        )
        merry = monsterFactory.create(
            name = "Merry",
            size = Size.SMALL,
            type = MonsterType.HUMANOID,
            challengeRating = 3,
            possibleAlignments = setOf(Alignment.NEUTRAL_GOOD),
            languages = setOf(Language.common, Language.halfling)
        )
        pippin = monsterFactory.create(
            name = "Pippin",
            size = Size.SMALL,
            type = MonsterType.HUMANOID,
            challengeRating = 3,
            possibleAlignments = setOf(Alignment.CHAOTIC_GOOD),
            languages = setOf(Language.common, Language.halfling)
        )
        sauron = monsterFactory.create(
            name = "Sauron",
            size = Size.HUGE,
            type = MonsterType.CELESTIAL,
            challengeRating = 3,
            possibleAlignments = setOf(Alignment.CHAOTIC_EVIL),
            languages = setOf(Language.common, Language.elvish, Language.dwarvish, Language.celestial)
        )

        entityManager.flush()
    }


    @Nested
    inner class FindByExample {

        @Test
        fun `test simple name with wildcard`() {
            val goin = monsterFactory.create(
                name = "Goin, Gimli's father"
            )

            entityManager.flush()

            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(name = "Gimli")
            )

            assertThat(retrieved)
                .hasSize(2)
                .containsExactlyInAnyOrder(
                    gimli, goin
                )
        }

        @Test
        fun `test simple name with lowercased`() {
            val goin = monsterFactory.create(
                name = "Goin, gimli's father"
            )

            entityManager.flush()

            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(name = "Gimli"))

            assertThat(retrieved)
                .hasSize(2)
                .containsExactlyInAnyOrder(
                    gimli, goin
                )
        }

        @Test
        fun `test simple name with spongebob-case term`() {
            entityManager.flush()

            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(name = "gImLi"))

            assertThat(retrieved)
                .hasSize(1)
                .containsExactlyInAnyOrder(
                    gimli
                )
        }

        @Test
        fun `test simple language`() {
            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(language = setOf(Language.elvish)))

            assertThat(retrieved)
                .hasSize(5)
                .containsExactlyInAnyOrder(
                    legolas,
                    aragorn,
                    gandalf,
                    frodo,
                    sauron
                )
        }

        @Test
        fun `test simple name`() {
            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(name = "Orc"))

            assertThat(retrieved)
                .hasSize(1)
                .containsExactlyInAnyOrder(
                    orc
                )
        }

        @Test
        fun `test simple alignment`() {
            val retrieved =
                monsterRecommendationService.findByExample(MonsterFilter(alignment = setOf(Alignment.CHAOTIC_EVIL)))

            assertThat(retrieved)
                .hasSize(2)
                .containsExactlyInAnyOrder(
                    orc,
                    sauron,
                )
        }

        @Test
        fun `test simple type`() {
            val retrieved =
                monsterRecommendationService.findByExample(MonsterFilter(type = setOf(MonsterType.HUMANOID)))

            assertThat(retrieved)
                .hasSize(7)
                .containsExactlyInAnyOrder(
                    legolas,
                    aragorn,
                    gimli,
                    frodo,
                    sam,
                    merry,
                    pippin
                )
        }

        @Test
        fun `test simple challengeRating`() {
            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(challengeRating = 2))

            assertThat(retrieved)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                    gandalf,
                    frodo,
                    sam,
                )
        }

        @Test
        fun `test simple size`() {
            val retrieved = monsterRecommendationService.findByExample(MonsterFilter(size = setOf(Size.LARGE)))

            assertThat(retrieved)
                .hasSize(1)
                .containsExactlyInAnyOrder(orc)
        }
    }

    @Nested
    inner class FindByExampleComplex {

        @Test
        fun `test complex with all`() {
            // test with all filters
            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(
                    name = "Orc",
                    size = setOf(Size.LARGE),
                    type = setOf(MonsterType.ABERRATION),
                    challengeRating = 1,
                    alignment = setOf(Alignment.CHAOTIC_EVIL),
                    language = setOf(Language.orc)
                )
            )

            assertThat(retrieved)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(orc)
        }

        @Test
        fun `test all humanoid speaking elvish`() {
            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(
                    type = setOf(MonsterType.HUMANOID),
                    language = setOf(Language.elvish)
                )
            )

            assertThat(retrieved)
                .hasSize(3)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(
                    legolas,
                    aragorn,
                    frodo,
                )
        }

        @Test
        fun `test all celestial and cr 3`() {
            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(
                    type = setOf(MonsterType.CELESTIAL), challengeRating = 3
                )
            )

            assertThat(retrieved)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(
                    sauron
                )
        }

        @Test
        fun `test all humanoid and speaking dwarvish and cr 2`() {
            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(
                    type = setOf(MonsterType.HUMANOID),
                    challengeRating = 2,
                    language = setOf(Language.dwarvish)
                )
            )

            assertThat(retrieved)
                .isEmpty()
        }

        @Test
        fun `test all cr 3 and language common and alignment LAWFUL_GOOD and humanoid`() {
            val retrieved = monsterRecommendationService.findByExample(
                MonsterFilter(
                    challengeRating = 3,
                    language = setOf(Language.common),
                    alignment = setOf(Alignment.CHAOTIC_GOOD),
                    type = setOf(MonsterType.HUMANOID)
                )
            )

            assertThat(retrieved)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(
                    pippin,
                )
        }
    }

}