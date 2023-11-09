package environment

import environment.globals.DateTime
import environment.globals.EnvGlobals
import environment.outdoors.classes.Outdoor
import renderer.SceneMemory

class Environment {
    //Указатель на глобальные переменные окружения
    lateinit var envGlobals: EnvGlobals
    //Указатель на outdoor
    lateinit var outdoor: Outdoor
    
    fun initEnvironment(dataPath: String) {
        //создание глобальных переменных окружения
        envGlobals = EnvGlobals()
        //генерация Outdoor
        outdoor = Outdoor(envGlobals)
        outdoor.genOutdoor("$dataPath/environment")
        SceneMemory.outdoor = outdoor
    }
    
    fun nextTick() {
        //следующий тик времени
        envGlobals.nextSimulationTime()

        println("${envGlobals.getExactDate(DateTime.DAYS).toInt()} " +
                "${envGlobals.getExactDate(DateTime.HOURS).toInt()}:" +
                "${envGlobals.getExactDate(DateTime.MINUTES).toInt()}:" +
                "${envGlobals.getExactDate(DateTime.SECONDS).toInt()}")

        //обработка перемещений людей
        for (householder in outdoor.getHouseholders()) {
            householder.doNextMovement(envGlobals.getExactDate(DateTime.HOURS), outdoor.getHouse())
            println("${householder.currentRoom.header} ${householder.mood}")
        }
        //Обработка изменения температур
        outdoor.getHouse().calculateTemperature(outdoor.getHouseholders())

        //Проверка состояния людей
        for (householder in outdoor.getHouseholders()) {
            householder.checkCondition(envGlobals.getExactDate(DateTime.HOURS))
        }

        //вызов действий нейронной сети
        for (floor in outdoor.getHouse().getFloors())
            for (room in floor.getRooms()) {
                room.nextStepNN(envGlobals.getExactDate(DateTime.DAYS), envGlobals.getExactDate(DateTime.HOURS))
                room.doNNResolve(envGlobals.getExactDate(DateTime.DAYS))
            }

        //Выполнение действий, заданных людьми
        for (floor in outdoor.getHouse().getFloors())
            for (room in floor.getRooms()) {
                room.doUserControlAction()
            }

        //вызов обратного распространения ошибки
        //вызов обновления весов нейронной сети
        for (floor in outdoor.getHouse().getFloors())
            for (room in floor.getRooms()) {
                room.teachNN()
            }
    }
}