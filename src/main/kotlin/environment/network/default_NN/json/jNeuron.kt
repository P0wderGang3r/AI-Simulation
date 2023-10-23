package environment.network.default_NN.json

import kotlinx.serialization.Serializable

@Serializable
class jNeuron (
    val name: String,
    val synapses: Array<String>,
    val activation: String,
    val parameters: Array<Double>
) {
}