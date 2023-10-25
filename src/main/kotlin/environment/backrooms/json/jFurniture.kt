package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jFurniture (
    val furniture_type: String,
    val coord_X: Int,
    val coord_Y: Int,
    val rotation: Int
)