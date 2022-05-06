package com.reference.dots_and_boxes_service.home

import com.reference.dots_and_boxes_service.core.URIPath.Companion.aboutPath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.gamesPath
import com.reference.dots_and_boxes_service.core.URIPath.Companion.indexPath
import com.reference.dots_and_boxes_service.models.entities.home.AboutEntity
import com.reference.dots_and_boxes_service.models.entities.home.IndexEntity
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink
import org.springframework.stereotype.Service

@Service
class HomeService {
    fun getIndexEntity(): SirenEntity {
        val links = getHomeLinks()
        return IndexEntity(links=links)
    }

    fun getAboutEntity(): SirenEntity {
        val properties = getAboutProperties()
        val links = getLinksToIndex()
        return AboutEntity(properties=properties, links=links)
    }

    private fun getAboutProperties(): Map<String, Any> {
        return mapOf("game-rules" to "The game starts with an empty grid of dots. Usually two players take turns adding a single horizontal or vertical line between two unjoined adjacent dots. A player who completes the fourth side of a 1×1 box earns one point and takes another turn. A point is typically recorded by placing a mark that identifies the player in the box, such as an initial. The game ends when no more lines can be placed. The winner is the player with the most points.[2][7] The board may be of any size grid. When short on time, or to learn the game, a 2×2 board (3×3 dots) is suitable.[8] A 5×5 board, on the other hand, is good for experts.[9]\n" +
                "\n" +
                "The diagram on the right shows a game being played on a 2×2 board (3×3 dots). The second player (\"B\") plays a rotated mirror image of the first player's moves, hoping to divide the board into two pieces and tie the game. But the first player (\"A\") makes a sacrifice at move 7 and B accepts the sacrifice, getting one box. However, B must now add another line, and so B connects the center dot to the center-right dot, causing the remaining unscored boxes to be joined together in a chain (shown at the end of move 8). With A's next move, A gets all three of them and ends the game, winning 3–1. ")
    }

    private fun getHomeLinks(): ArrayList<SirenLink> {
        val links: ArrayList<SirenLink> = ArrayList<SirenLink>()
        links.add(SirenLink(title="Index", rel=arrayOf("self"), href=indexPath()))
        links.add(SirenLink(title="Games List", rel=arrayOf("collection") ,href=gamesPath()))
        links.add(SirenLink(title="About", rel=arrayOf("about"), href=aboutPath()))
//        links.add(SirenLink(title="Client Registration", rel=arrayOf("rules"), href= URI("/dots-and-boxes/client-registration")))
        return links
    }

    private fun getLinksToIndex(): ArrayList<SirenLink> {
        return arrayListOf(
            SirenLink(title="About", rel=arrayOf("self"), href=aboutPath()),
            SirenLink(title="Index", rel=arrayOf("index"), href=indexPath())
        )
    }

}