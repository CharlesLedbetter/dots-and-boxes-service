package com.reference.dots_and_boxes_service.line

import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamesPath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.linePath
import com.reference.dots_and_boxes_service.models.entities.game.LineEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import javax.persistence.*

@Entity
@Table(name="Line")
class Line (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    var id: Long?,

    @Column(name = "MARKED")
    var marked: Boolean = false
) {
    fun sirenSubEntity() : LineEntity {
        return LineEntity(marked=marked, links=getLineLinks(true))
    }

    private fun getLineLinks(isSubEntity: Boolean): ArrayList<SirenLink> {
        val links = arrayListOf(
            SirenLink(title="Line", rel=arrayOf("self"), href=linePath(id!!))
        )
        if (!isSubEntity) {
            links.add(SirenLink(title="Placeholder Square", rel=arrayOf("game"), href=gamesPath()))
        }
        return links
    }
}