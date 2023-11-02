package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType

interface Thing {
    val type: DeviceType
    val room: Room

    fun doTheWork(input: Any): Double
}