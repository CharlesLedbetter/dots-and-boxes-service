package com.reference.dots_and_boxes_service.game

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GameRepository: JpaRepository<Game, Long> {
    fun save(game:Game): Optional<Game>

    fun findAllByOwner(owner: String): Iterable<Game>
}