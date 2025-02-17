package at.jku.deq.ddbeyond.api

import at.jku.deq.ddbeyond.api.dto.AuthDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping

internal interface AuthApi {
    @PostMapping(
        "/cobalt-token",
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getToken(@CookieValue("CobaltSession") cobalt: String): AuthDto
}