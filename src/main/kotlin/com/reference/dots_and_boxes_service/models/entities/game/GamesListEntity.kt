package com.reference.dots_and_boxes_service.models.entities.game

import com.reference.dots_and_boxes_service.core.URIPath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamesPath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.indexPath
import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenField
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import org.springframework.http.HttpMethod

class GamesListEntity (
    entities: ArrayList<SirenEntity>? = null
) : SirenEntity (
    `class`=arrayOf("games-list"),
    title="Games List",
    properties=mapOf("owner" to "CJL"),
    entities=entities,
    links=arrayListOf(
        SirenLink(title="Game List", rel=arrayOf("self"), href=gamesPath()),
        SirenLink(title="Index", rel=arrayOf("index"), href=indexPath())
    ),
    actions= arrayListOf(
        SirenAction(
            title="New Game",
            name="new-game",
            method=HttpMethod.POST,
            fields=arrayListOf(
                SirenField(
                title="Name",
                name="name"
                ),
                SirenField(
                    title="Height",
                    name="height",
                    type="number"
                ),
                SirenField(
                    title="Width",
                    name="width",
                    type="number"
                ),
                SirenField(
                    title="Players",
                    name="player"
                )
            ),
            href=gamesPath()
        )
    )
)