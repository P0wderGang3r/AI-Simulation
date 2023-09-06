import network.default_NN.classes.Network
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

    val globalNN = Network()

    globalNN.genNeuralNetwork("data/global_NN.json")
    globalNN.printNetwork()

    val roomFirstNN = Network()

    roomFirstNN.genNeuralNetwork("data/room_first_NN.json")
    roomFirstNN.printNetwork()

    val runtime = Runtime()
    Thread(runtime).start()
    runtime.requestRun()

}

//TODO: -> Планировка здания
//TODO: -> Изменение координат комнат
//TODO: -> Объединение комнат между собой (тайлы?)

//TODO: -> Условия окружающей среды

//TODO: -> Написать алгоритм парсинга дома
//TODO: -> Написать алгоритм парсинга температуры
//TODO: -> Написать алгоритм парсинга окружения

//TODO: Разработать алгоритмы поведения устройств
//TODO: -> Считывание данных об окружающей среде
//TODO: -> Разработка алгоритмов влияния на окружающую среду

//TODO: Структура глобальной нейронной сети

//TODO: Разработать алгоритмы поведения людей
//TODO: -> Разработка карты перемещений
//TODO: -> Разработка алгоритма поиска кратчайшего пути (с элементами случайности)
//TODO: -> Разработка распорядка дня
//TODO: -> Нейронная сеть ?????

//TODO: Обучение нейронной сети

//TODO: графический вывод информации
//TODO: opengl / vulkan
//TODO: -> отрисовка треугольника
//TODO: -> отрисовка текстуры треугольника
//TODO: -> отрисовка конвейера объектов
//TODO: -> органы управления камерой в трёхмерной сцене
//TODO: -> определение нажатия по области сцены
//TODO: -> определение направления обзора
//TODO: -> скрытие объектов при определённых углах обзора

//TODO: Доделать комнаты, разнообразить комнаты

//TODO: -> модели
//TODO: -> текстуры
//TODO: -> ...