package com.reference.dots_and_boxes_service.line

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LineRepository: JpaRepository<Line, Long> {
    fun save(line: Line): Optional<Line>
}