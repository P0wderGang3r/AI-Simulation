package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jComplexModel (
    val model: String,
    val texture: String,
    val scale_X: Double,
    val scale_Y: Double,
    val scale_Z: Double,
    val color_R: Double,
    val color_G: Double,
    val color_B: Double,
    val joints: Array<jJoint>
)