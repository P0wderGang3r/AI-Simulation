package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType

class Thermometer(
    override val room: Room,
    override val type: DeviceType
) : Thing {
    override fun doTheWork(input: Any): Double {
        return room.getTemperature()
    }
}