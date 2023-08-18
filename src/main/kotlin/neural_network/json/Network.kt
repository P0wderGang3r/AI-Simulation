package neural_network.json

import kotlinx.serialization.Serializable

@Serializable
class Network (
    val layers: Array<Array<Neuron>>
) {
}