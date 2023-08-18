package neural_network.json

import kotlinx.serialization.Serializable

@Serializable
class Neuron (
    val number: Int,
    val synapses: Array<Int>,
    val activation: String
) {
}