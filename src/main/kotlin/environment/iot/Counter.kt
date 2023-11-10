package environment.iot

import environment.backrooms.classes.Room
import environment.globals.DeviceType

class Counter(
    override val room: Room,
    override val type: DeviceType
): Thing {
    private var countedHouseholders = 0
    override fun doTheWork(input: Any): Double {
        val change = input as Int

        if (change == 0) {
            countedHouseholders = 0
        }
        if (change == -1) {
            if (countedHouseholders > 0)
                countedHouseholders -= 1
        }

        if (change == 1) {
            countedHouseholders += 1
        }

        if (change > 1) {
            countedHouseholders = change
        }

        return countedHouseholders.toDouble()
    }
}