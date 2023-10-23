package environment.network.default_NN.json

import kotlinx.serialization.Serializable

@Serializable
class jNetwork (
    val header: String,
    val layers: Array<Array<jNeuron>>
) {
}