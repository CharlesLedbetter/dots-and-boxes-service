package com.reference.dots_and_boxes_service.square

import com.reference.dots_and_boxes_service.line.Line
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface SquareRepository: JpaRepository<Square, Long> {
    fun save(square: Square): Optional<Square>

    @Query("""
        FROM Square WHERE
        up_line = :line
        OR right_line = :line
        OR down_line = :line
        OR left_line = :line
    """)
    fun findSquaresByLineId(line: Line): Iterable<Square>
}