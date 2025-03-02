package at.jku.deq.server.controller

import at.jku.deq.api.DynamicEncounterApi
import at.jku.deq.service.dto.SyncDtoService
import org.springframework.web.bind.annotation.RestController

@RestController
internal class DynamicEncounterController(
    private val syncDtoService: SyncDtoService
) : DynamicEncounterApi {
    override fun sync() {
        syncDtoService.sync()
    }
}