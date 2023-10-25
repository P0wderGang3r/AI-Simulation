package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoomTypes (
    val header: String,
    val rooms: Array<jRoomType>
)