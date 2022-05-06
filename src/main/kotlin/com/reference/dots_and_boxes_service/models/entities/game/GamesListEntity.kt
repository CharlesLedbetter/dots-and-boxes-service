package com.reference.dots_and_boxes_service.models.entities.game

import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamesPath
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class GamesListEntity (
    entities: ArrayList<SirenEntity>? = null
) : SirenEntity (
    `class`=arrayOf("games-list"),
    title="Games List",
    properties=mapOf("owner" to "CJL"),
    links=arrayListOf(SirenLink(title="Game List", rel=arrayOf("self"), href=gamesPath())),
    entities=entities
)