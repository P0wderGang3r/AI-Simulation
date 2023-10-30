import environment.globals.DateTime
import environment.network.classes.Network
import renderer.WindowInitializer

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

    val globalNN = Network()

    globalNN.genNeuralNetwork("./data/environment/default_house/NN_global.json")
    globalNN.printNetwork()

    val roomFirstNN = Network()

    roomFirstNN.genNeuralNetwork("./data/environment/default_house/NN_first.json")
    roomFirstNN.printNetwork()

    val roomSecondNN = Network()

    roomSecondNN.genNeuralNetwork("./data/environment/default_house/NN_second.json")
    roomSecondNN.printNetwork()

    val roomThirdNN = Network()

    roomThirdNN.genNeuralNetwork("./data/environment/default_house/NN_third.json")
    roomThirdNN.printNetwork()


    val roomNoneNN = Network()

    roomNoneNN.genNeuralNetwork("./data/environment/default_house/NN_none.json")
    roomNoneNN.printNetwork()

    println(DateTime.DAYS.getPrecise(1000000000.0))
    println(DateTime.DAYS.getRaw(1000000000.0))

    println(DateTime.HOURS.getPrecise(1000000000.0))
    println(DateTime.HOURS.getRaw(1000000000.0))

    println(DateTime.MINUTES.getPrecise(1000000000.0))
    println(DateTime.MINUTES.getRaw(1000000000.0))

    println(DateTime.SECONDS.getPrecise(1000000000.0))
    println(DateTime.SECONDS.getRaw(1000000000.0))

    println(DateTime.MILLISECONDS.getPrecise(1000000000.0))
    println(DateTime.MILLISECONDS.getRaw(1000000000.0))

    //val runtime = Runtime()
    //Thread(runtime).start()
    val windowInitializer = WindowInitializer()
    windowInitializer.run()

}

//TODO: Структура глобальной нейронной сети
//TODO: Обучение нейронной сети
//TODO: Проверка работы нейронной сети

//TODO: Исправить ошибку конкуренции данных ErrorType


//TODO: карта соприкасающихся комнат
//TODO: -> Алгоритм создания дверных проёмов между комнатами

//TODO: Разработать алгоритмы поведения людей
//TODO: -> Разработка карты перемещений
//TODO: -> Разработка алгоритма поиска кратчайшего пути (с элементами случайности)
//TODO: -> Разработка распорядка дня

//TODO: счётчик людей в комнате

//TODO: графический вывод информации
//TODO: -> органы управления камерой в трёхмерной сцене
//TODO: -> определение нажатия по области сцены
//TODO: -> определение направления обзора
//TODO: -> скрытие объектов при определённых углах обзора

//TODO: -> модели
//TODO: -> текстуры
//TODO: -> ...
//TODO: -> разнообразить комнаты