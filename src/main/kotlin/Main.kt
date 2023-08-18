import neural_network.classes.Network

fun main(args: Array<String>) {
    var path: String = ""

    for (index in args.indices) {
        if (args[index] == "--base" && index < args.size - 1) {
            path = args[index + 1]
        }
    }

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Args: ${args.joinToString()}\n")

    val network = Network()

    network.genNeuralNetwork(path)
    network.printNetwork()
}

//TODO: Функции активации
//TODO: Обучение нейронной сети
//TODO: Спецификация входных данных нейронной сети
//TODO: Возможность импорта весов
//TODO: Возможность экспорта весов