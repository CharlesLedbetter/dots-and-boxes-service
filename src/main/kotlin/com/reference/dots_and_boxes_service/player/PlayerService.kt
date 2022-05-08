package com.reference.dots_and_boxes_service.player

import com.reference.dots_and_boxes_service.game.Game
import org.springframework.stereotype.Service

@Service
class PlayerService(private val repository: PlayerRepository) {
    fun createPlayers(players: Array<String>, game: Game) {
        for ((idx, player_name) in players.withIndex()) {
            val order = (idx+1).toLong()
            val player = Player(null, game, order, player_name)
            repository.save(player).orElseThrow { Exception("Bad")}
        }
    }

    fun getCurrentPlayer(game: Game): Player {
        return repository.findCurrentPlayerOfGame(game, game.turn).orElseThrow { Exception("Bad") }
    }

    fun getPlayersOfGame(game: Game): Array<Player> {
        return repository.findAllPlayersOfGame(game).toList().toTypedArray()
    }
}