package com.reference.dots_and_boxes_service.square

import com.reference.dots_and_boxes_service.game.Game
import com.reference.dots_and_boxes_service.game.GameService
import com.reference.dots_and_boxes_service.line.Line
import com.reference.dots_and_boxes_service.line.LineService
import com.reference.dots_and_boxes_service.models.entities.game.SquareEntity
import com.reference.dots_and_boxes_service.models.enums.Direction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.player.Player
import com.reference.dots_and_boxes_service.player.PlayerService
import org.springframework.stereotype.Service

@Service
class SquareService(private val repository: SquareRepository, private val playerService: PlayerService, private val lineService: LineService) {
    fun getSquare(id: Long) : Square {
        return repository.findById(id).orElseThrow { Exception("bad") }
    }

    fun markLineOfSquare(square: Square, direction: Direction): Boolean {
        var newTurn = true
        if(square.owner == null) {
            val line = lineService.markLine(square.getLine(direction))
            repository.findSquaresByLineId(line).forEach {
                if (it.up.marked && it.right.marked && it.down.marked && it.left.marked) {
                    val player: Player = playerService.getCurrentPlayer(square.game)
                    it.owner = player.name
                    repository.save(it)
                    newTurn = false
                }
            }
        } else {
            throw Exception("bad")
        }
        return newTurn
    }

    fun createSquaresForGame(game: Game, height: Long, width: Long): ArrayList<SirenEntity> {
        val numberOfLines = ((height + 1) * width) + ((width + 1) * height)
        val lines = lineService.createLinesForGame(numberOfLines)
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
                repository.save(square).ifPresent { squareEntities.add(it.sirenSubEntity()) }
            }
        }
        return squareEntities as ArrayList<SirenEntity> // TODO research this
    }

    fun checkAllSquaresAreOwned(game: Game): Boolean {
        return repository.findAllUnownedSquaresOfGame(game).any()
    }

    fun getAllSquaresOwnedBy(game: Game, player: Player): Array<Square> {
        return repository.findAllSquaresOwnedByPlayer(game, player.name).toList().toTypedArray()
    }
}