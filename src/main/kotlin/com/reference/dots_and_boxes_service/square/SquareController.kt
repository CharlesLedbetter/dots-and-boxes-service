package com.reference.dots_and_boxes_service.square

import com.reference.dots_and_boxes_service.game.GameService
import com.reference.dots_and_boxes_service.models.enums.Direction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dots-and-boxes/squares", produces = ["application/vdn.siren+json"])
class SquareController(private val squareService: SquareService, private val gameService: GameService) {
    @GetMapping("/{id}")
    fun getSquare(@PathVariable id: Long): SirenEntity {
        return squareService.getSquare(id).sirenEntity()
    }

    @PostMapping("/{id}/mark-line")
    fun markLineOfSquare(@PathVariable id: Long, @RequestParam direction: Direction): SirenEntity {
        val square = squareService.getSquare(id)
        squareService.markLineOfSquare(square, direction)
        return gameService.getGameEntity(square.game.id!!)
    }
}