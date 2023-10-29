package environment.network.json

import kotlinx.serialization.Serializable

@Serializable
class jLayer(
    val learnable: Boolean,
    val neurons: Array<jNeuron>
)