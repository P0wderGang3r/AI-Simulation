import environment.Environment
import environment.globals.DateTime
import environment.network.classes.Network
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

    val environment = Environment()
    environment.initEnvironment("./data/environment/outdoor.json")

    val runtime = Runtime(environment)
    Thread(runtime).start()

    val windowInitializer = WindowInitializer(runtime)
    windowInitializer.run()
}


//TODO: случайное место человека в комнате

//TODO: модель человека

//TODO: Исправить ошибку конкуренции данных ErrorType