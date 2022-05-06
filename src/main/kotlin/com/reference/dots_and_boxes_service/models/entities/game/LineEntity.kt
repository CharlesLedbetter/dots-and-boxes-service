package com.reference.dots_and_boxes_service.models.entities.game

import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class LineEntity (
        marked: Boolean,
        actions: ArrayList<SirenAction>? = null,
        links: ArrayList<SirenLink>? = null
) : SirenEntity (
        `class`=arrayOf("line"),
        title="Line",
        properties=mapOf("marked" to marked),
        links=links,
        actions=actions
)