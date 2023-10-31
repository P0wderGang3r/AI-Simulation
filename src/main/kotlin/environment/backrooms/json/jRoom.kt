package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoom (
    val header: String,
    val name: String,
    val room_type: String,
    val coord_X: Int,
    val coord_Y: Int,
    val path_NN: String,
    val connections: Array<String>,
    val devices: Array<jDevice>,
    val furniture: jFurniture
){
}