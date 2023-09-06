package network.default_NN.json

import kotlinx.serialization.Serializable

@Serializable
class jNetwork (
    val layers: Array<Array<jNeuron>>
) {
}