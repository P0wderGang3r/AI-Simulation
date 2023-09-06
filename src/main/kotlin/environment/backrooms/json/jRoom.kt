package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jRoom (
    val name: String,
    val description: String,
    val room_type: String,
    val coord_X: Int,
    val coord_Y: Int,
    val path_NN: String,
    val devices: Array<jDevice>
){
}