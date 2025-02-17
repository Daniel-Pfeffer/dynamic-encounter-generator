package at.jku.deq.ddbeyond.api

import at.jku.deq.ddbeyond.api.dto.DndBeyondMonsterApiResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

internal interface DndApi {
    @GetMapping("Monster")
    fun getMonsterPaginated(
        @RequestParam search: String = "",
        @RequestParam skip: Int = 0,
        @RequestParam take: Int = 100,
        @RequestParam showHomebrew: Char = 'f'
    ): DndBeyondMonsterApiResult
}

