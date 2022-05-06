package com.reference.dots_and_boxes_service.models.entities.home

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class AboutEntity (
        properties: Map<String, Any>? = null,
        links: ArrayList<SirenLink>? = null
) : SirenEntity(
        `class`=arrayOf("rules"),
        title="Rules of Dots and Boxes",
        properties=properties,
        links=links
)
