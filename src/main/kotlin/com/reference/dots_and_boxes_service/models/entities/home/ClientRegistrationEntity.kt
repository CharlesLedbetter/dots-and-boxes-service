package com.reference.dots_and_boxes_service.models.entities.home

import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class ClientRegistrationEntity (
        entities: ArrayList<SirenEntity>? = null,
        links: ArrayList<SirenLink>? = null,
        actions: ArrayList<SirenAction>? = null
) : SirenEntity(
        `class`=arrayOf("registration"),
        title="Your Clients",
        entities=entities,
        links=links,
        actions=actions
)