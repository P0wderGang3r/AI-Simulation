package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jHouse (
    val header: String,
    val path_NN: String,
    val path_room_types: String,
    val floors: Array<Array<jRoom>>
)