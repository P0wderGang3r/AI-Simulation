package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoomType (
    val header: String,
    val room_type: String,
    val size_X: Int,
    val size_Y: Int,
    val furniture: Array<jFurniture>
)