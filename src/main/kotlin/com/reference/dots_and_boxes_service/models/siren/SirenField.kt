package com.reference.dots_and_boxes_service.models.siren

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class SirenField (
        var title: String? = null,
        var name: String,
        var `class`: Array<String>? = null,
        var type: String = "text",
        var value: Any? = null,
)