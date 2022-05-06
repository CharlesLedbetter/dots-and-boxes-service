package com.reference.dots_and_boxes_service.game

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/dots-and-boxes/games", produces = ["application/vdn.siren+json"])
class GameController(private val service: GameService) {
    @GetMapping()
    fun getAllGames(): SirenEntity {
        return service.getGamesEntity()
    }

    @PostMapping()
    fun newGame(gameForm: GameForm): SirenEntity {
        return service.createNewGame(gameForm.game())
    }

    @GetMapping("/{id}")
    fun getGame(@PathVariable id: Long): SirenEntity {
        return service.getGameEntity(id)
    }
}

data class GameForm(
        val name: String,
        val width: Long,
        val height: Long,
        val playerCount: Long
) {
    fun game() = Game(id=null, name=this.name, width=width, height=height, owner=getOwner(), playerCount=playerCount)

    private fun getOwner(): String = "CJL"
}
