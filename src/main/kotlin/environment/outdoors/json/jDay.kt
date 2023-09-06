package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jDay (
    val temperatures: Array<jTemperature>
){
}