package com.reference.dots_and_boxes_service.models.entities.game

import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class SquareEntity (
    x: Long,
    y: Char,
    owner: String? = null,
    links: ArrayList<SirenLink>? = null,
    actions: ArrayList<SirenAction>? = null,
    entities: ArrayList<SirenEntity>? = null
) : SirenEntity (
    `class`=arrayOf("square"),
    title="$x$y Square",
    properties=mapOf("x" to x, "y" to y, "owner" to owner),
    links=links,
    actions=actions,
    entities=entities
)