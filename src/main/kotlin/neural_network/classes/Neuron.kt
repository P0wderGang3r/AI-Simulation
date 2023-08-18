package neural_network.classes

import neural_network.enums.ActivationFunction

class Neuron (
    private val activationFunction: ActivationFunction,
    private val inputNetwork: Array<Synapse>,
    private val neuronNumber: Int = 0
) {
    private var signal: Double = 0.0

    fun pushSignal(): Double {
        return activationFunction.calculate(signal)
    }

    fun pullSignal() {
        this.signal = 0.0

        for (synapse in inputNetwork) {
            signal += synapse.pullSignal()
        }
    }

    fun getSignal(): Double {
        return signal
    }

    fun getSynapses(): ArrayList<String> {
        var outputWeights = ArrayList<String>()
        for (output in inputNetwork)
            outputWeights.add("${output.getNetElementNumber()} : ${output.weight}")
        return outputWeights
    }

    fun getActivationFunction(): String {
        return activationFunction.toString()
    }

    fun getNumber(): Int {
        return neuronNumber
    }
}