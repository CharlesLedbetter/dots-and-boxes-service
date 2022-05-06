package com.reference.dots_and_boxes_service.models.entities.home

import com.reference.dots_and_boxes_service.models.siren.SirenAction
import com.reference.dots_and_boxes_service.models.siren.SirenEntity
import com.reference.dots_and_boxes_service.models.siren.SirenLink

class ClientEntity (
        clientId: String = "",
        properties: Map<String, Any>? = null,
        links: ArrayList<SirenLink>? = null,
        actions: ArrayList<SirenAction>? = null
) : SirenEntity(
        `class`=arrayOf("registration"),
        title="Client $clientId",
        properties=properties,
        links=links,
        actions=actions
)