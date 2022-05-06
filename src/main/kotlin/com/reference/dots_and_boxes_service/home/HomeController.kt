package com.reference.dots_and_boxes_service.home

import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/dots-and-boxes", produces = ["application/vdn.siren+json"])
class HomeController(private val service: HomeService) {
    @GetMapping()
    fun home(): SirenEntity = service.getIndexEntity()

    @GetMapping("about")
    fun about(): SirenEntity {
        return service.getAboutEntity()
    }
}
