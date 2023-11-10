package environment.network.json

import kotlinx.serialization.Serializable

@Serializable
class jNetwork (
    val header: String,
    val layers: Array<jLayer>
)