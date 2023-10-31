package environment.network.classes

class Synapse(
    private val nextElement: Neuron,
    private val prevElement: Neuron,
    private var weight: Double = 0.0
) {
    fun getWeight(): Double = weight
    fun updateWeight(learnSpeed: Double) {
        weight -= nextElement.pushSignal() * prevElement.pushError() * learnSpeed
    }

    fun pullError(): Double {
        return nextElement.pushError()
    }

    fun pullSignal(): Double {
        return prevElement.pushSignal()
    }

    fun getNextElementNumber(): String {
        return nextElement.name
    }

    fun getPrevElementNumber(): String {
        return prevElement.name
    }
}