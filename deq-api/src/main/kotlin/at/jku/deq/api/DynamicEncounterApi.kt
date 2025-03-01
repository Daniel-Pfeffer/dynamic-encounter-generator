package at.jku.deq.api

import org.springframework.web.bind.annotation.PostMapping

interface DynamicEncounterApi {
    @PostMapping("sync")
    fun sync()
}