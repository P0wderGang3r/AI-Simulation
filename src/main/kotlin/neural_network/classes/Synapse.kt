package neural_network.classes

class Synapse(
    private val netElement: Neuron,
    var weight: Double = 0.0
) {
    fun pullSignal(): Double {
        return netElement.pushSignal()
    }

    fun getNetElementNumber(): Int {
        return netElement.getNumber()
    }
}