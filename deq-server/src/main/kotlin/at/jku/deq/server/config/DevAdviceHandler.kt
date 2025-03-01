package at.jku.deq.server.config

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class DevAdviceHandler {
    companion object {
        private val LOG = KotlinLogging.logger { }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException, req: HttpServletRequest): ErrorResponse {
        LOG.error("Request: " + req.requestURL + " raised " + ex);

        return ErrorResponse(ex.message ?: "Entity not found")
    }

    @Serializable
    data class ErrorResponse(
        val message: String,
    )
}