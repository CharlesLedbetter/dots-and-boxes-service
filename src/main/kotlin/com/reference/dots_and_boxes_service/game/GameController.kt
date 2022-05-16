package com.reference.dots_and_boxes_service.game

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/dots-and-boxes/games", produces = ["application/vdn.siren+json"])
@Validated
class GameController(private val service: GameService) {
    @GetMapping
    fun getAllGames(): SirenEntity {
        return service.getGamesEntity()
    }

    @PostMapping
    fun newGame(@Valid gameForm: GameForm): SirenEntity {
        return service.createNewGame(gameForm.game(), gameForm.player)
    }

    @GetMapping("/{id}")
    fun getGame(@PathVariable id: Long): SirenEntity {
        return service.getGameEntity(id)
    }
}

data class GameForm(
        @field:NotBlank(message = "Field 'name' cannot be blank")
        @field:Size(max = 50, message="Field 'name' must be 50 characters or less")
        val name: String,
        @field:Min(3, message="Field 'width' must be a minimum of 3 and a maximum of 26 characters long")
        @field:Max(26, message="Field 'width' must be a minimum of 3 and a maximum of 26 characters long")
        val width: Long,
        @field:Min(3, message="Field 'height' must be a minimum of 3 and a maximum of 26 characters long")
        @field:Max(26, message="Field 'height' must be a minimum of 3 and a maximum of 26 characters long")
        val height: Long,
        @field:Size(min = 2, max = 10, message="Field 'player' must include at least 2 and at most 10 players")
        val player: Array<String>
) {
    fun game() = Game(id=null, name=this.name, width=width, height=height, owner=getOwner(), null, playerCount=getPlayerCount(player))

    private fun getPlayerCount(player: Array<String>): Long {
        if (player.size > 1 && player.size < 11) {
            return player.size.toLong()
        } else {
            throw Exception("bad")
        }
    }

    private fun getOwner(): String = "CJL"
}
