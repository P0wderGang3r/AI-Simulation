package environment.backrooms.classes

import environment.backrooms.json.jDevice
import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomType
import environment.network.default_NN.classes.Network
import environment.network.default_NN.enums.ErrorType

class Room {
    var header: String = ""

    var coord_X: Int = 0

    var coord_Y: Int = 0

    var localNN: Network = Network()

    var furniture = ArrayList<Furniture>()

    var devices = ArrayList<Device>()

    var roomTypeString: String = ""

    var roomType = RoomType()

    fun getDevices() = devices

    fun genRoomByType(roomTypesJSON: Array<jRoomType>): ErrorType {
        for (roomTypeJSON in roomTypesJSON) {
            if (roomTypeString == roomTypeJSON.room_type) {
                roomType.genRoomType(roomTypeJSON)
            }
        }

        return ErrorType.OK
    }

    fun genDevices(devicesJSON: Array<jDevice>) {
        devices.clear()
        for (deviceJSON in devicesJSON) {
            when (deviceJSON.device_type) {
                "THERMOMETER" -> devices.add(Device.THERMOMETER)
                "CONDITIONER" -> devices.add(Device.CONDITIONER)
                "COUNTER" -> devices.add(Device.COUNTER)
            }
        }
    }

    fun genRoom(roomJSON: jRoom): ErrorType {
        this.header = roomJSON.header
        this.coord_X = roomJSON.coord_X
        this.coord_Y = roomJSON.coord_Y
        this.roomTypeString = roomJSON.room_type

        localNN.genNeuralNetwork(roomJSON.path_NN)

        genDevices(roomJSON.devices)

        return ErrorType.OK
    }
}