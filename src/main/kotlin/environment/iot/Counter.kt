package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType
import environment.householders.Householder
import environment.householders.Householder1

class Counter(
    override val room: Room,
    override val type: DeviceType,
    householders: ArrayList<Householder>
): Thing {
    private var householders = ArrayList<Householder>()
    override fun doTheWork(input: Any): Double {
        var counter = 0.0
        for (householder in householders) {
            if (householder.currentRoom == room) {
                counter += 1
            }
        }
        return counter
    }

    init {
        this.householders.addAll(householders)
    }
}