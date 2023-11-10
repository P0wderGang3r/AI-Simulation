package environment.outdoors.json

import kotlinx.serialization.Serializable

@Serializable
class jWeather (
    val header: String,
    val days: Array<jDay>
)