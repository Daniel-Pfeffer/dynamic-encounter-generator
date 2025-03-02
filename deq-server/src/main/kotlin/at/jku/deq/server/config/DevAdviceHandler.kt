package at.jku.deq.server.config

import jakarta.persistence.EntityNotFoundException
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
internal class DevAdviceHandler : ResponseEntityExceptionHandler() {
    companion object {
        private val LOG = KotlinLogging.logger { }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(
        ex: EntityNotFoundException,
        req: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val servletRequest = req as ServletWebRequest
        LOG.error { "${servletRequest.request.requestURI} raised $ex" }
        return handleExceptionInternal(
            ex,
            ErrorResponse(ex.message ?: "Entity not found"),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            req
        ) as ResponseEntity<ErrorResponse>
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        req: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val servletRequest = req as ServletWebRequest
        LOG.error { "${servletRequest.request.requestURI} raised $ex" }
        return handleExceptionInternal(
            ex,
            ErrorResponse(ex.message ?: "Bad request"),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            req
        ) as ResponseEntity<ErrorResponse>
    }

    @Serializable
    internal data class ErrorResponse(
        val message: String,
    )
}