package com.reference.dots_and_boxes_service.models.siren

import com.fasterxml.jackson.annotation.JsonInclude
import java.net.URI

@JsonInclude(JsonInclude.Include.NON_NULL)
class SirenLink (
        var title: String? = null,
        var `class`: Array<String>? = null,
        var rel: Array<String>,
        var href: URI,
        var type: String? = null
)