package global_NN.classes

import jsonParser
import kotlinx.serialization.decodeFromString
import logs
import global_NN.enums.ActivationFunction
import global_NN.enums.ErrorType
import global_NN.json.Network
import java.io.File

class Network {
    private var name = ""
    private val network = ArrayList<ArrayList<Neuron>>()

    private fun fileParser(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val networkDesc: Network
        try {
            networkDesc = jsonParser.decodeFromString(jsonText)
        } catch (e: Exception) {
            return ErrorType.ParseError
        }

        if (logs[0]) {
            println("____/Input JSON\\____")
            for (layer in networkDesc.layers) {
                for (neuron in layer) {
                    print("|-> ${neuron.activation} : [ ")
                    for (synapse in neuron.synapses) print("$synapse ")
                    print("]\n")
                }
                println("--------------------")
            }
        }

        val result = ErrorType.OK
        result.data = networkDesc
        return result
    }

    private fun activationParser(
        activation: String
        ): ActivationFunction {

        return when (activation) {
            "none" -> ActivationFunction.NONE
            "threshold" -> ActivationFunction.THRESHOLD
            else -> ActivationFunction.NONE
        }
    }

    fun genNeuralNetwork(path: String): ErrorType {
        val parseResult = fileParser(path)

        if (parseResult != ErrorType.OK)
            return parseResult

        name = path

        val networkJSON = parseResult.data as Network

        for (layer in 0 until networkJSON.layers.size)
            network.add(ArrayList())

        //Проходимся от первого до последнего слоя НС
        for (layerIndex in 0 until networkJSON.layers.size) {
            //Для каждого нейрона в слое выполняем некоторые действия
            for (neuronJSON in networkJSON.layers[layerIndex]) {
                //Создаём множество синапсов
                val synapses = ArrayList<Synapse>(networkJSON.layers.size)

                //Если слой не первый, то создаём соединения
                if (layerIndex > 0) {
                    //Уравниваем влияние синапсов на нейрон
                    val weight = 1.0 / neuronJSON.synapses.size
                    //Проходимся по списку предполагаемым синапсов до индексов нейронов
                    for (synapse in neuronJSON.synapses) {
                        //Проходимся по всем индексам нейронов в следующем слое
                        for (neuronPrevLayerJSON in network[layerIndex - 1])
                            //Если есть такой нейрон по номеру, синапс для которого было определён,то ...
                            if (neuronPrevLayerJSON.getName() == synapse)
                            //... создаём синапс на основе данных, прочитанных из файла
                                synapses.add(
                                    Synapse(
                                        neuronPrevLayerJSON,
                                        weight = weight
                                    )
                                )
                    }
                }

                //Создаём нейрон
                network[layerIndex].add(
                    Neuron(
                        neuronJSON.name,
                        synapses.toTypedArray(),
                        activationParser(
                            neuronJSON.activation
                        ),
                        neuronJSON.parameters
                    )
                )

            }
        }

        return ErrorType.OK
    }

    fun getNetwork(): ArrayList<ArrayList<Neuron>> {
        return network
    }

    fun printNetwork() {
        var amount = 0
        val header = "___/ Network: \"$name\" \\___"

        println(header)
        for (layer in network) {
            for (neuron in layer) {
                amount++
                print("|-> ${neuron.getName()} - ${neuron.getActivationFunction()} ( ")
                for (parameter in neuron.getActivationParameters()) print("$parameter")
                print(" ) - ")
                for (synapse in neuron.getSynapses()) print("[ $synapse ]")
                if (neuron.getSynapses().size == 0) print("NO SYNAPSES")
                print("\n")
            }
            for (length in 0 until header.count()) print("-")
            println()
        }
        println("Neuron amount: $amount\n")
    }
}