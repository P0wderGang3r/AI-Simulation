package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jOutdoor (
    val header: String,
    val path_temperatures: String,
    val houses: Array<String>
){
}