package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jParameters(
    val scene_size: FloatArray,
    val scene_displacement: FloatArray,
    val scene_rotation: FloatArray,
    val scene_ambient_lightning: FloatArray
)