package environment.network.classes

class Synapse(
    private val netElement: Neuron,
    var weight: Double = 0.0
) {
    fun pullError(): Double {
        return netElement.pushError()
    }

    fun pullSignal(): Double {
        return netElement.pushSignal()
    }

    fun getNetElementNumber(): String {
        return netElement.name
    }
}