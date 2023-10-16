package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jMap (
    val parameters: jParameters,
    val objects: Array<jObjects>
)