package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jObjects(
    val name: String,
    val position: FloatArray,
    val size: Float,
    val model: String,
    val texture: String
)