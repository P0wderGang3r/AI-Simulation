package global_NN.json

import kotlinx.serialization.Serializable

@Serializable
class Neuron (
    val name: String,
    val synapses: Array<String>,
    val activation: String,
    val parameters: Array<Double>
) {
}