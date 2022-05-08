package com.reference.dots_and_boxes_service.game

import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamePath
import com.reference.dots_and_boxes_service.models.entities.game.GameEntity
import com.reference.dots_and_boxes_service.models.entities.game.GamesListEntity
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import com.reference.dots_and_boxes_service.square.Square
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.persistence.*

@Entity
@Table(name="Dots_Boxes_Game")
class Game (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID")
    var id: Long?,

    @Column(name="GAME_NAME")
    var name: String = "placeholder",

    @Column(name="WIDTH")
    var width: Long,

    @Column(name="HEIGHT")
    var height: Long,

    @Column(name="OWNER")
    var owner: String,

    @Column(name="WINNER")
    var winner: String? = null,

    @Column(name="TURN")
    var turn: Long = 1,

    @Column(name="NUMBER_OF_PLAYERS")
    var playerCount: Long = 2
) {
    @OneToMany(mappedBy = "game")
    var squares: MutableCollection<Square> = arrayListOf()

    fun sirenSubEntity() : GameEntity {
        return GameEntity(
            title=name,
            properties=mapOf(
                "name" to name,
                "owner" to owner,
                "winner" to winner,
                "width" to width,
                "height" to height,
                "turn" to turn,
                "player-count" to playerCount
            ),
            links=getGameLinks(true)
        )
    }

    fun sirenEntity(player: String, squares: ArrayList<SirenEntity>? = null) : GameEntity {
        return GameEntity(
            title=name,
            properties=mapOf(
                "name" to name,
                "owner" to owner,
                "winner" to winner,
                "width" to width,
                "height" to height,
                "turn" to player,
                "player-count" to playerCount
            ),
            links=getGameLinks(false),
            entities=squares ?: getSquares()
        )
    }

    private fun getSquares(): ArrayList<SirenEntity> {
        return ArrayList(squares.map { it.sirenSubEntity() })
    }

    private fun getGameLinks(isSubEntity: Boolean): ArrayList<SirenLink> {
        val links = arrayListOf(
            SirenLink(title=name, rel=arrayOf("self"), href=gamePath(id!!))
        )
        if (!isSubEntity) {
            links.add(SirenLink(title="Games", rel=arrayOf("collection", "game"), href=gamesPath()))
        }
        return links
    }

    fun gamesPath() = URI(
        ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("dots-and-boxes/games")
            .toUriString())

}

fun Iterable<Game>.sirenEntity() : GamesListEntity {
    return GamesListEntity(
        entities=this.map { it.sirenSubEntity() } as ArrayList<SirenEntity>
    )
}