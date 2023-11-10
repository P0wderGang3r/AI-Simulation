package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jDay (
    val day: Int,
    val temperatures: Array<jTemperature>
)