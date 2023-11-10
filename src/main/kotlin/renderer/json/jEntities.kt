package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jEntities (
    val entities: Array<jEntityPath>
)