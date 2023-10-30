package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType
import environment.globals.TemperatureSource

class Conditioner(
    override val room: Room,
    override val type: DeviceType
): Thing {
    private var currentTemperature = 24.0

    fun increaseTemperature() {
        currentTemperature += 1
    }

    fun decreaseTemperature() {
        currentTemperature -= 1
    }

    fun getCurrentTemperature(): Double = currentTemperature

    override fun doTheWork(): Double {
        room.changeTemperature(TemperatureSource.ROOM, currentTemperature)

        return currentTemperature
    }
}