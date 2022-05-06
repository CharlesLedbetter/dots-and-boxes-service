package com.reference.dots_and_boxes_service.models.siren

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("title", "class", "rel", "properties", "entities", "actions", "links")
open class SirenEntity (
        var `class`: Array<String>? = null,
        var rel: Array<String>? = null,
        open var title: String? = null,
        open var properties: Map<String, Any?>? = null,
        open var entities: ArrayList<SirenEntity>? = null,
        open var actions: ArrayList<SirenAction>? = null,
        open var links: ArrayList<SirenLink>? = null

)