package com.reference.dots_and_boxes_service.game

import com.reference.dots_and_boxes_service.line.LineRepository
import com.reference.dots_and_boxes_service.models.entities.game.SquareEntity
import com.reference.dots_and_boxes_service.line.Line
import com.reference.dots_and_boxes_service.square.Square
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.square.SquareRepository
import org.springframework.stereotype.Service

@Service
class GameService(private val gameRepository: GameRepository, private val lineRepository: LineRepository, private val squareRepository: SquareRepository) {
    fun createNewGame(game: Game): SirenEntity {
        val newGame = gameRepository.save(game).orElseThrow { Exception("bad") }
        val squares = createSquaresForGame(newGame, newGame.height, newGame.width)
        return newGame.sirenEntity(squares)
    }

    private fun createSquaresForGame(game: Game, height: Long, width: Long): ArrayList<SirenEntity> {
        val numberOfLines = ((height + 1) * width) + ((width + 1) * height)
        val lines: ArrayList<Line> = arrayListOf()
        for (i in 1..numberOfLines) {
            val line = Line(null, false)
            lineRepository.save(line).ifPresent { lines.add(it) }
        }
        val numberOfSquares = (height * width) - 1
        val squareEntities : ArrayList<SquareEntity> = arrayListOf()
        for (i in 0 .. numberOfSquares) {
            val xOffset = (i % width)
            val yOffset = (i / width).toInt()
            val offset = (width + width + 1) * yOffset

            val x = xOffset + 1
            val y = 'a' + yOffset
            val topLine = (xOffset + offset).toInt()
            val rightLine = (xOffset + width + 1 + offset).toInt()
            val bottomLine = (xOffset + width + width + 1 + offset).toInt()
            val leftLine = (xOffset + width + offset).toInt()

            if (lines.size > 0) {
                val square = Square(null, game, x, y, null, lines[topLine], lines[rightLine], lines[bottomLine], lines[leftLine])
                print("gameID: ${square.game.id} SquareGameID: ${square.game.id}\n")
                squareRepository.save(square).ifPresent { squareEntities.add(it.sirenSubEntity()) }
            }
        }
        return squareEntities as ArrayList<SirenEntity> // TODO research this
    }

    fun getGameEntity(id: Long) : SirenEntity {
        return gameRepository.findById(id).orElseThrow { Exception("bad") }.sirenEntity()
    }

    fun getGamesEntity(): SirenEntity {
        return gameRepository.findAllByOwner("CJL").sirenEntity()
    }

    fun updateTurn(game: Game) {
        game.turn = (game.turn % game.playerCount) + 1
        gameRepository.save(game)
    }
}