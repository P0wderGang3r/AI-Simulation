package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jEntity (
    val name: String,
    val scale_X: Double,
    val scale_Y: Double,
    val scale_Z: Double,
    val complexes: Array<jComplexModel>
)