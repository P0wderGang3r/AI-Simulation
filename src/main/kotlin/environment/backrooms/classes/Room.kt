package environment.backrooms.classes

class Room(
    val name: String
) {
    private val devices = ArrayList<Device>()

    fun getDevices() = devices


}