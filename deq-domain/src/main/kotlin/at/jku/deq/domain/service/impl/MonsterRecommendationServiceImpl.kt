package at.jku.deq.domain.service.impl

import at.jku.deq.domain.entity.*
import at.jku.deq.domain.model.MonsterFilter
import at.jku.deq.domain.repository.MonsterRepository
import at.jku.deq.domain.service.MonsterRecommendationService
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class MonsterRecommendationServiceImpl(
    private val monsterRepository: MonsterRepository,
) : MonsterRecommendationService {
    @Transactional(readOnly = true)
    override fun findByExample(filter: MonsterFilter): List<Monster> {

        val specification = Specification.allOf(
            filter.alignment?.let { hasAnyAlignment(it) },
            filter.language?.let { hasLanguage(it) },
            filter.environment?.let { hasEnvironment(it) },
            filter.name?.let { nameLike(it) },
            filter.type?.let { hasAnyType(it) },
            filter.size?.let { hasAnySize(it) },
            filter.challengeRating?.let {
                Specification { root, _, cb ->
                    cb.equal(
                        root.get<Int>("challengeRating"),
                        it
                    )
                }
            },
        )

        return monsterRepository.findAll(specification)
    }

    private fun hasAnySize(types: Set<Size>): Specification<Monster>? {
        return if (types.isEmpty()) null
        else Specification { root, _, cb ->
            if (types.size == 1) {
                return@Specification cb.equal(root.get<Size>("size"), types.first())
            }

            val isMemberClauses = types.map {
                cb.equal(root.get<Size>("size"), it)
            }

            cb.or(*isMemberClauses.toTypedArray())
        }
    }

    private fun hasAnyType(types: Set<MonsterType>): Specification<Monster>? {
        return if (types.isEmpty()) null
        else Specification { root, _, cb ->
            if (types.size == 1) {
                return@Specification cb.equal(root.get<MonsterType>("type"), types.first())
            }

            val isMemberClauses = types.map {
                cb.equal(root.get<MonsterType>("type"), it)
            }

            cb.or(*isMemberClauses.toTypedArray())
        }
    }

    private fun nameLike(name: String): Specification<Monster> {
        return Specification { root, _, cb ->
            cb.like(cb.lower(root.get("name")), "%$name%".lowercase())
        }
    }

    private fun hasAnyAlignment(alignments: Set<Alignment>): Specification<Monster>? {
        return if (alignments.isEmpty()) null
        else Specification { root, _, cb ->
            if (alignments.size == 1) {
                return@Specification cb.isMember(alignments.first(), root.get<Set<Alignment>>("possibleAlignments"))
            }

            val isMemberClauses = alignments.map {
                cb.isMember(it, root.get<Set<Alignment>>("possibleAlignments"))
            }

            cb.or(*isMemberClauses.toTypedArray())
        }
    }

    private fun hasEnvironment(environments: Set<Environment>): Specification<Monster>? {
        return if (environments.isEmpty()) null
        else Specification { root, _, cb ->
            if (environments.size == 1) {
                return@Specification cb.isMember(environments.first(), root.get<Set<Environment>>("environments"))
            }

            val isMemberClauses = environments.map {
                cb.isMember(it, root.get<Set<Environment>>("environments"))
            }
            cb.or(*isMemberClauses.toTypedArray())
        }
    }

    private fun hasLanguage(languages: Set<Language>): Specification<Monster>? {
        return if (languages.isEmpty()) null
        else Specification { root, _, cb ->
            if (languages.size == 1) {
                return@Specification cb.isMember(languages.first(), root.get<Set<Language>>("languages"))
            }

            val isMemberClauses = languages.map {
                cb.isMember(it, root.get<Set<Language>>("languages"))
            }
            cb.or(*isMemberClauses.toTypedArray())
        }
    }

}