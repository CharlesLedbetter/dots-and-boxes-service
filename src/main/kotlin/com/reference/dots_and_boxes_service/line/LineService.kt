package com.reference.dots_and_boxes_service.line

import org.springframework.stereotype.Service

@Service
class LineService(private val repository: LineRepository) {
    fun markLine(line: Line): Line {
        if(!line.marked) {
            line.marked = true
            return repository.save(line).orElseThrow { Exception("bad") }
        } else {
            throw Exception("bad")
        }
    }

    fun createLinesForGame(numberOfLines: Long): ArrayList<Line> {
        val lines: ArrayList<Line> = arrayListOf()
        for (i in 1..numberOfLines) {
            val line = Line(null, false)
            repository.save(line).ifPresent { lines.add(it) }
        }
        return lines
    }

}