package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoomPreset (
    val header: String,
    val room_preset: String,
    val size_X: Int,
    val size_Y: Int,
    val furniture: Array<jFurniture>
)