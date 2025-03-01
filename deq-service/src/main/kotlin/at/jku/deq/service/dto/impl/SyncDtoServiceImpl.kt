package at.jku.deq.service.dto.impl

import at.jku.deq.service.dto.SyncDtoService
import at.jku.deq.service.service.SyncService
import org.springframework.stereotype.Service

@Service
internal class SyncDtoServiceImpl(
    private val syncService: SyncService
) : SyncDtoService {
    override fun sync() {
        syncService.sync()
    }
}