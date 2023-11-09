package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoomPresets (
    val header: String,
    val rooms: Array<jRoomPreset>
)