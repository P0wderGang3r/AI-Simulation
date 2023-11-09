import environment.Environment
import environment.globals.DateTime
import environment.network.classes.Network
import renderer.WindowInitializer
import runtime.Runtime

fun main(args: Array<String>) {
    for (index in args.indices) {
        if (args[index] == "--base" && index < args.size - 1) {
            data_path = args[index + 1]
        }
    }

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Args: ${args.joinToString()}\n")

    val environment = Environment()
    environment.initEnvironment(data_path)

    val runtime = Runtime(environment)
    Thread(runtime).start()

    val windowInitializer = WindowInitializer(runtime)
    windowInitializer.run()
}

//TODO: модель человека

//TODO: оценка качества работы нейронной сети

//TODO: тайловый рендеринг земли

//TODO: облака

//TODO: Исправить ошибку конкуренции данных ErrorType