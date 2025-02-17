package at.jku.deq.ddbeyond.api.client

import at.jku.deq.ddbeyond.api.AuthApi
import org.springframework.cloud.openfeign.FeignClient


@FeignClient(
    name = "AuthApiClient",
    url = "\${dndbeyond.api.auth}",
)
internal interface AuthApiClient : AuthApi
