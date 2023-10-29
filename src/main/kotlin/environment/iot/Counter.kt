package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType

class Counter(
    override val room: Room,
    override val type: DeviceType
): Thing {
    override fun doTheWork(): Double {
        TODO("Not yet implemented")
    }

}