package com.reference.dots_and_boxes_service.square

import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamePath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.squareMarkLinePath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.squarePath
import com.reference.dots_and_boxes_service.game.Game
import com.reference.dots_and_boxes_service.line.Line
import com.reference.dots_and_boxes_service.models.enums.Direction
import com.reference.dots_and_boxes_service.models.entities.game.SquareEntity
import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenField
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import org.springframework.http.HttpMethod
import javax.persistence.*

@Entity
@Table(name="Square")
class Square (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQUARE_ID")
    var id: Long?,

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    var game: Game,

    @Column(name="SQUARE_X")
    var x: Long,

    @Column(name="SQUARE_Y")
    var y: Char,

    @Column(name="SQUARE_OWNER")
    var owner: String? = null,

    @OneToOne
    @JoinColumn(name="UP_LINE")
    var up: Line,

    @OneToOne
    @JoinColumn(name="RIGHT_LINE")
    var right: Line,

    @OneToOne
    @JoinColumn(name="DOWN_LINE")
    var down: Line,

    @OneToOne
    @JoinColumn(name="LEFT_LINE")
    var left: Line
) {
    fun sirenSubEntity() : SquareEntity {
        return SquareEntity(
            x=x,
            y=y,
            owner=owner,
            links=getSquareLinks(true),
            actions=getActions(false),
            entities=getEntities()
        )
    }

    fun sirenEntity() : SquareEntity {
        return SquareEntity(
            x=x,
            y=y,
            owner=owner,
            links=getSquareLinks(true),
            entities=getEntities()
        )
    }

    private fun getSquareLinks(isSubEntity: Boolean): ArrayList<SirenLink> {
        val links = arrayListOf(
            SirenLink(title="$x$y Squares", rel=arrayOf("self"), href= squarePath(id!!))
        )
        if (!isSubEntity) {
            links.add(SirenLink(title=game.name, rel=arrayOf("game"), href=gamePath(game.id!!)))
        }
        return links
    }

    private fun getEntities(): ArrayList<SirenEntity> {
        val entities = ArrayList<SirenEntity>()
        entities.add(up.sirenSubEntity())
        entities.add(right.sirenSubEntity())
        entities.add(down.sirenSubEntity())
        entities.add(left.sirenSubEntity())
        return entities
    }

    private fun getActions(isSubEntity: Boolean): ArrayList<SirenAction>? {
        val actions = ArrayList<SirenAction>()
        val fields = arrayListOf(
            SirenField(
            title="Line Direction",
            name="direction"
            )
        )
        actions.add(SirenAction(
            title="Mark Line",
            name="mark-line",
            method=HttpMethod.POST,
            fields=fields,
            href=squareMarkLinePath(id!!)
        ))
        return actions
    }

    fun getLine(direction: Direction): Line =
        when (direction) {
            Direction.UP -> up
            Direction.RIGHT -> right
            Direction.DOWN -> down
            Direction.LEFT -> left
        }
}