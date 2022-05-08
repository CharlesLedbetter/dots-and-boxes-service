package com.reference.dots_and_boxes_service.game

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.player.Player
import com.reference.dots_and_boxes_service.player.PlayerService
import com.reference.dots_and_boxes_service.square.SquareService
import org.springframework.stereotype.Service

@Service
class GameService(private val repository: GameRepository, private val playerService: PlayerService, private val squareService: SquareService) {
    fun createNewGame(game: Game, players: Array<String>): SirenEntity {
        val newGame = repository.save(game).orElseThrow { Exception("bad") }
        playerService.createPlayers(players, game)
        val firstPlayer = players[0]
        val squares = squareService.createSquaresForGame(newGame, newGame.height, newGame.width)
        return newGame.sirenEntity(firstPlayer, squares)
    }

    fun getGameEntity(id: Long) : SirenEntity {
        val game = repository.findById(id).orElseThrow { Exception("bad") }
        val player: Player = playerService.getCurrentPlayer(game)
        return game.sirenEntity(player.name)
    }

    fun getGamesEntity(): SirenEntity {
        return repository.findAllByOwner("CJL").sirenEntity()
    }

    fun updateTurn(game: Game) {
        game.turn = (game.turn % game.playerCount) + 1
        repository.save(game)
    }

    fun checkForEndState(game: Game) {
        if(!squareService.checkAllSquaresAreOwned(game)) {
            val players = playerService.getPlayersOfGame(game)
            val playerMap: MutableMap<String, Int> = mutableMapOf()
            players.forEach {
                val playerSize = squareService.getAllSquaresOwnedBy(game, it).size
                playerMap.put(it.name, playerSize)
            }
            val topPlayerValue = playerMap.maxOf { it.value }
            val filteredMap = playerMap.filterValues { it == topPlayerValue }
            if (filteredMap.size == 1) {
                game.winner = filteredMap.firstNotNullOf { it.key }
            } else {
                game.winner = "Draw - No Winner"
            }
            repository.save(game)
        }
    }
}