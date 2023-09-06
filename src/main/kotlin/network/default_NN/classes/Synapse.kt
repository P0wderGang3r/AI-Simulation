package network.default_NN.classes

class Synapse(
    private val netElement: Neuron,
    var weight: Double = 0.0
) {
    fun pullSignal(): Double {
        return netElement.pushSignal()
    }

    fun getNetElementNumber(): String {
        return netElement.getName()
    }
}