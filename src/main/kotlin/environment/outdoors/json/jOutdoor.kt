package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jOutdoor (
    val header: String,
    val path_weather: String,
    val path_house: String,
    val path_outdoor_map: String
){
}