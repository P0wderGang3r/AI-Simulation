package environment.backrooms.json

import kotlinx.serialization.Serializable

@Serializable
class jHouse (
    val header: String,
    val path_NN: String,
    val floors: Array<Array<jRoom>>
)