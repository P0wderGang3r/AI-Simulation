package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType
import environment.globals.TemperatureSource

class Conditioner(
    override val room: Room,
    override val type: DeviceType
): Thing {
    private var currentTemperature = 16.0

    var isEnabled = false

    fun getCurrentTemperature(): Double = currentTemperature

    override fun doTheWork(input: Any): Double {
        if (isEnabled)
            room.changeTemperature(TemperatureSource.ROOM, currentTemperature)

        return currentTemperature
    }
}