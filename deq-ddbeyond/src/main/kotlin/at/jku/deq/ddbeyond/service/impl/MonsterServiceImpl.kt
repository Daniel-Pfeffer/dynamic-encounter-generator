package at.jku.deq.ddbeyond.service.impl

import at.jku.deq.ddbeyond.api.client.DndApiClient
import at.jku.deq.ddbeyond.api.dto.DndBeyondMonster
import at.jku.deq.ddbeyond.dto.DeqMonster
import at.jku.deq.ddbeyond.mapper.toDeqMonster
import at.jku.deq.ddbeyond.service.MonsterService
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
internal class MonsterServiceImpl(
    private val dndApiClient: DndApiClient
) : MonsterService {

    companion object {
        private const val PAGE_SIZE = 100
    }

    override fun getAllMonsters(): List<DeqMonster> {
        val monsters = getAllMonstersUserHasAccessTo()

        return monsters.map { it.toDeqMonster() }
    }

    private fun getAllMonstersUserHasAccessTo(): List<DndBeyondMonster> {
        val first = dndApiClient.getMonsterPaginated(take = PAGE_SIZE)

        val allPages = ceil(first.pagination.total / PAGE_SIZE.toDouble()).toInt()

        return (first.data + callPaginated(
            1,
            allPages
        )).filter { it.isReleased /*as far as I've seen if isReleased then user has the content*/ }
    }

    private fun callPaginated(page: Int, totalPages: Int): List<DndBeyondMonster> {
        if (page > totalPages) return emptyList()

        return dndApiClient.getMonsterPaginated(skip = page * PAGE_SIZE, take = PAGE_SIZE).data + callPaginated(
            page + 1,
            totalPages
        )
    }
}