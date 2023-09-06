package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jTemperature (
    val hours: Int,
    val minutes: Int,
    val temperature: Double
){
}