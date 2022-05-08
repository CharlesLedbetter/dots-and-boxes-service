package com.reference.dots_and_boxes_service.player

import com.reference.dots_and_boxes_service.game.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface PlayerRepository: JpaRepository<Player, Long> {
    fun save(player: Player): Optional<Player>

    @Query("""
        FROM Player WHERE
        game = :game
        AND player_order = :turn
    """)
    fun findCurrentPlayerOfGame(game: Game, turn: Long): Optional<Player>

    @Query("""
        FROM Player WHERE
        game = :game
    """)
    fun findAllPlayersOfGame(game: Game): Iterable<Player>
}