package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jJoint (
    val name: String,
    val coord_X: Double,
    val coord_Y: Double,
    val coord_Z: Double,
    val rotation_X: Double,
    val rotation_Y: Double,
    val rotation_Z: Double
)