package com.reference.dots_and_boxes_service.square

import com.reference.dots_and_boxes_service.game.GameService
import com.reference.dots_and_boxes_service.line.LineService
import com.reference.dots_and_boxes_service.models.enums.Direction
import org.springframework.stereotype.Service

@Service
class SquareService(private val repository: SquareRepository, private val lineService: LineService, private val gameService: GameService) {
    fun getSquare(id: Long) : Square {
        return repository.findById(id).orElseThrow { Exception("bad") }
    }

    fun markLineOfSquare(square: Square, direction: Direction) {
        if(square.owner == null) {
            val line = lineService.markLine(square.getLine(direction))
            var newTurn = true
            repository.findSquaresByLineId(line).forEach {
                if (it.up.marked && it.right.marked && it.down.marked && it.left.marked) {
                    it.owner = square.game.turn
                    repository.save(it)
                    newTurn = false
                }
            }

            if (newTurn) {
                gameService.updateTurn(square.game)
            }
        } else {
            throw Exception("bad")
        }
    }
}