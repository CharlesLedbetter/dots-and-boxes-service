package com.reference.dots_and_boxes_service.models.entities.game

import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class GameEntity (
    title: String = "Game",
    properties: Map<String, Any>? = null,
    links: ArrayList<SirenLink>? = null,
    entities: ArrayList<SirenEntity>? = null,
    actions: ArrayList<SirenAction>? = null
) : SirenEntity (
    `class`=arrayOf("game"),
    title=title,
    properties=properties,
    links=links,
    entities=entities,
    actions=actions
)