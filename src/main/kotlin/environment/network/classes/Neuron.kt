package environment.network.classes

import environment.network.enums.ActivationFunction

class Neuron (
    val name: String = "",
    val synapses: ArrayList<Synapse>,
    val errorConnections: ArrayList<Synapse>,
    private val activationFunction: ActivationFunction,
    private val activationParameters: Array<Double> = arrayOf()
) {
    var signal: Double = 0.0

    //--------------------------------------------------------------------------------------------РАСПРОСТРАНЕНИЕ ОШИБКИ

    fun pushError(): Double {
        return TODO()
    }

    fun pullError() {
        var error = 0.0
        for (errorConnection in errorConnections) {
            error += errorConnection.pullError()
        }
    }

    //-------------------------------------------------------------------------------------------РАСПРОСТРАНЕНИЕ СИГНАЛА

    fun pushSignal(): Double {
        return activationFunction.calculate(activationParameters, signal)
    }

    fun pullSignal() {
        this.signal = 0.0

        for (synapse in synapses) {
            signal += synapse.pullSignal()
        }
    }

    fun getSynapsesString(): ArrayList<String> {
        val outputWeights = ArrayList<String>()
        for (output in synapses)
            outputWeights.add("${output.getNetElementNumber()} : ${output.weight}")
        return outputWeights
    }

    fun getActivationFunction(): ActivationFunction {
        return activationFunction
    }

    fun getActivationParameters(): Array<Double> {
        return activationParameters
    }
}