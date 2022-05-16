package com.reference.dots_and_boxes_service.core

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.URI
import javax.validation.ConstraintViolationException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    // TODO add link logic
    fun handle(ex: ConstraintViolationException) =
        status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.valueOf("application/vnd.siren+json"))
            .body(
                SirenEntity(
                `class`=arrayOf("exception"),
                title="Malformed Game",
                properties=mapOf("problem" to ex.message),
                links=arrayListOf(
                    SirenLink(
                        title="",
                        rel=arrayOf(""),
                        href= URI("http://localhost")
                    )
                )
            )
    )
}