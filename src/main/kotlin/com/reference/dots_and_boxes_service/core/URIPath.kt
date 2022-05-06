package com.reference.dots_and_boxes_service.core

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

class URIPath {
    companion object {
        fun indexPath() = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes")
                .toUriString()
        )

        fun aboutPath() = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/about")
                .toUriString()
        )

        fun gamesPath() = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/games")
                .toUriString()
        )

        fun gamePath(id: Long) = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/games/$id")
                .toUriString()
        )

        fun squarePath(id: Long) = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/squares/$id")
                .toUriString()
        )

        fun squareMarkLinePath(id: Long) = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/squares/$id/mark-line")
                .toUriString()
        )

        fun linePath(id: Long) = URI(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("dots-and-boxes/lines/$id")
                .toUriString()
        )
    }
}