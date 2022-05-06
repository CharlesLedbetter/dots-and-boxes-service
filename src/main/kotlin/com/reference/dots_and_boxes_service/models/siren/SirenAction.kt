package com.reference.dots_and_boxes_service.models.siren

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.net.URI

@JsonInclude(JsonInclude.Include.NON_NULL)
class SirenAction (
        var title: String? = null,
        var name: String,
        var `class`: Array<String>? = null,
        var href: URI,
        var method: HttpMethod = HttpMethod.GET,
        var type: String = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        var fields: ArrayList<SirenField>? = null
)