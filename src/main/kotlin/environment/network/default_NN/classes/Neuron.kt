package environment.network.default_NN.classes

import environment.network.default_NN.enums.ActivationFunction

class Neuron (
    private val name: String = "",
    private val synapses: Array<Synapse>,
    private val activationFunction: ActivationFunction,
    private val activationParameters: Array<Double> = arrayOf()
) {
    private var signal: Double = 0.0

    fun pushSignal(): Double {
        return activationFunction.calculate(activationParameters, signal)
    }

    fun pullSignal() {
        this.signal = 0.0

        for (synapse in synapses) {
            signal += synapse.pullSignal()
        }
    }

    fun getSignal(): Double {
        return signal
    }

    fun getSynapses(): ArrayList<String> {
        var outputWeights = ArrayList<String>()
        for (output in synapses)
            outputWeights.add("${output.getNetElementNumber()} : ${output.weight}")
        return outputWeights
    }

    fun getName(): String {
        return name
    }

    fun getActivationFunction(): ActivationFunction {
        return activationFunction
    }

    fun getActivationParameters(): Array<Double> {
        return activationParameters
    }
}