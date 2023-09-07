package environment.backrooms.classes

import network.default_NN.classes.Network

class Room(
    var name: String,
    var coord_X: Int,
    var coord_Y: Int
) {
    private lateinit var localNN: Network

    private val furniture = ArrayList<Furniture>()

    private val devices = ArrayList<Device>()

    fun getDevices() = devices


}