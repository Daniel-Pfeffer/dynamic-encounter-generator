package at.jku.deq.ddbeyond.api.client

import at.jku.deq.ddbeyond.api.DndApi
import at.jku.deq.ddbeyond.api.config.DndBeyondFeignConfig
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(
    "dnd-api",
    url = "\${dndbeyond.api.monster}",
    configuration = [DndBeyondFeignConfig::class]
)
internal interface DndApiClient : DndApi