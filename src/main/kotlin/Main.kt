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


//TODO: -> Исправить ошибку конкуренции данных ErrorType

//TODO: Разработать алгоритмы поведения устройств
//TODO: -> Считывание данных об окружающей среде
//TODO: -> Разработка алгоритмов влияния на окружающую среду

//TODO: карта соприкасающихся комнат
//TODO: -> Алгоритм создания дверных проёмов между комнатами

//TODO: Разработать алгоритмы поведения людей
//TODO: -> Разработка карты перемещений
//TODO: -> Разработка алгоритма поиска кратчайшего пути (с элементами случайности)
//TODO: -> Разработка распорядка дня

//TODO: Структура глобальной нейронной сети
//TODO: Обучение нейронной сети
//TODO: Проверка работы нейронной сети


//TODO: графический вывод информации
//TODO: -> органы управления камерой в трёхмерной сцене
//TODO: -> определение нажатия по области сцены
//TODO: -> определение направления обзора
//TODO: -> скрытие объектов при определённых углах обзора

//TODO: -> модели
//TODO: -> текстуры
//TODO: -> ...
//TODO: -> разнообразить комнаты