package environment.householders

import environment.backrooms.classes.House
import environment.backrooms.classes.Room

interface Householder {
    val priority: Int

    var mood: Int

    var currentRoom: Room

    var coord_X: Int

    var coord_Y: Int

    val householders: ArrayList<Householder>

    //Матрица переходов
    val dayTimeMovementMatrix: Array<DoubleArray>
    val nightTimeMovementMatrix: Array<DoubleArray>

    //Минимальное и максимальное приемлемое значение температуры
    val dayPreferredCondition: DoubleArray
    val nightPreferredCondition: DoubleArray

    /**
     * @param time Текущий час
     */
    fun doNextMovement(time: Double, house: House) {
        val currentPlace = when (currentRoom.name) {
            "hall" -> 0
            "ladder_1" -> 1
            "kitchen" -> 2
            "ladder_2" -> 3
            "bedroom" -> 4

            else -> 0
        }

        val randomNumber = Math.random()

        var accumulator = 0.0

        var isDay = false

        if (time > 10 && time < 22)
            isDay = true

        var nextMovement = currentPlace

        //Получаем комнату для перехода
        if (isDay) {
            for (index in dayTimeMovementMatrix[currentPlace].indices) {
                accumulator += dayTimeMovementMatrix[currentPlace][index]
                if (accumulator > randomNumber) {
                    nextMovement = index
                    break
                }
            }
        } else {
            for (index in nightTimeMovementMatrix[currentPlace].indices) {
                accumulator += nightTimeMovementMatrix[currentPlace][index]
                if (accumulator > randomNumber) {
                    nextMovement = index
                    break
                }
            }
        }

        val destinationRoomName = when (nextMovement) {
            0 -> "hall"
            1 -> "ladder_1"
            2 -> "kitchen"
            3 -> "ladder_2"
            4 -> "bedroom"

            else -> "hall"
        }

        if (currentPlace != nextMovement)
            currentRoom.leaveHandler()

        for (floor in house.getFloors()) {
            for (room in floor.getRooms()) {
                if (room.name == destinationRoomName) {
                    currentRoom = room
                }
            }
        }

        currentRoom.enterHandler()
    }

    private fun checkPriority(): Boolean {
        var isPrioritized = true
        for (householder in householders) {
            if (householder.priority > priority && householder.currentRoom == currentRoom) {
                isPrioritized = false
                break
            }
        }
        return isPrioritized
    }

    /**
     * @param time текущий час
     * @return -1 - ничего не менять, 0 - выключить устройство, 1 - включить устройство
     */
    fun checkCondition(time: Double) {
        var isDay = false

        if (time > 10 && time < 22)
            isDay = true

        //Если дневное время суток, то...
        if (isDay) {
            //Если в промежутке приятного пребывания, то повышается настроение
            if (currentRoom.getTemperature() > dayPreferredCondition[0] &&
                currentRoom.getTemperature() < dayPreferredCondition[1]) {
                if (mood < 100) mood += 1
            } else {
                //иначе понижается
                if (mood > 0) mood -= 1
                //Если больше максимального, включаем
                if (currentRoom.getTemperature() > nightPreferredCondition[1]) {
                    if (checkPriority()) {
                        currentRoom.lastControlActions.clear()
                        currentRoom.lastControlActions.add(1)
                        return
                    }
                }
                //Если меньше минимального, выключаем
                if (currentRoom.getTemperature() < nightPreferredCondition[0]) {
                    if (checkPriority()) {
                        currentRoom.lastControlActions.clear()
                        currentRoom.lastControlActions.add(0)
                        return
                    }
                }
            }
        //То же самое, только для ночного времени суток
        } else {
            if (currentRoom.getTemperature() > nightPreferredCondition[0] &&
                currentRoom.getTemperature() < nightPreferredCondition[1]) {
                if (mood < 100) mood += 1
            } else {
                if (mood > 0) mood -= 1
                if (currentRoom.getTemperature() > nightPreferredCondition[1]) {
                    if (checkPriority()) {
                        currentRoom.lastControlActions.clear()
                        currentRoom.lastControlActions.add(1)
                        return
                    }
                }
                if (currentRoom.getTemperature() < nightPreferredCondition[0]) {
                    if (checkPriority()) {
                        currentRoom.lastControlActions.clear()
                        currentRoom.lastControlActions.add(0)
                        return
                    }
                }
            }
        }

        if (checkPriority()) {
            currentRoom.lastControlActions.clear()
            currentRoom.lastControlActions.add(-1)
        }
    }
}