package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jTexture(
    val name: String,
    val path: String,
    val width: Int,
    val height: Int
)