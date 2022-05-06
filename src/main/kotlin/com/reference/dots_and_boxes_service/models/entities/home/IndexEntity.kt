package com.reference.dots_and_boxes_service.models.entities.home

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class IndexEntity (
        links: ArrayList<SirenLink>? = null
) : SirenEntity(
        `class`=arrayOf("index"),
        title="Welcome to Dots and Boxes Game",
        links=links
)