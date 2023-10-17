import renderer.WindowInitializer
import runtime.Runtime

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

    /*
    val globalNN = Network()

    globalNN.genNeuralNetwork("./data/house_first/NN_global.json")
    globalNN.printNetwork()

    val roomFirstNN = Network()

    roomFirstNN.genNeuralNetwork("./data/house_first/NN_room.json")
    roomFirstNN.printNetwork()
     */

    //val runtime = Runtime()
    //Thread(runtime).start()
    val windowInitializer = WindowInitializer()
    windowInitializer.run()

}

//TODO: -> Написать алгоритм парсинга окружения
//TODO: -> Написать алгоритм парсинга комнат
//TODO: -> Написать алгоритм парсинга погоды

//TODO: графический вывод информации
//TODO: -> органы управления камерой в трёхмерной сцене
//TODO: -> определение нажатия по области сцены
//TODO: -> определение направления обзора
//TODO: -> скрытие объектов при определённых углах обзора

//TODO: -> Исправить ошибку конкуренции данных ErrorType

//TODO: Разработать алгоритмы поведения устройств
//TODO: -> Считывание данных об окружающей среде
//TODO: -> Разработка алгоритмов влияния на окружающую среду

//TODO: Структура глобальной нейронной сети

//TODO: Разработать алгоритмы поведения людей
//TODO: -> Разработка карты перемещений
//TODO: -> Разработка алгоритма поиска кратчайшего пути (с элементами случайности)
//TODO: -> Разработка распорядка дня

//TODO: Обучение нейронной сети

//TODO: Доделать комнаты, разнообразить комнаты

//TODO: -> модели
//TODO: -> текстуры
//TODO: -> ...