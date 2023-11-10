package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jTemperature (
    val hour: Int,
    val minute: Int,
    val temperature: Double
)