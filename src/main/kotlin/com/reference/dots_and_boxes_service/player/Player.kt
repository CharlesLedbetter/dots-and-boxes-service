package com.reference.dots_and_boxes_service.player

import com.reference.dots_and_boxes_service.game.Game
import javax.persistence.*

@Entity
@Table(name="Player")
class Player (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    var id: Long?,

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    var game: Game,

    @Column(name="PLAYER_ORDER")
    var order: Long = 1,

    @Column(name="PLAYER_NAME")
    var name: String = "playerID $id"
)