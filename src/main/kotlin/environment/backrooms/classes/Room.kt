package environment.backrooms.classes

import environment.backrooms.json.jDevice
import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomType
import environment.globals.TemperatureSource
import environment.globals.DeviceType
import environment.network.classes.Network
import environment.network.enums.ErrorType

class Room {
    var header: String = ""

    var coord_X: Int = 0

    var coord_Y: Int = 0

    var localNN: Network = Network()

    var roomTypeString: String = ""

    var roomType = RoomType()


    //--------------------------------------------------------------------------------------------------TEMPERATURE ZONE

    private var temperature = 24.0

    fun getTemperature(): Double = temperature

    fun changeTemperature(sourceName: TemperatureSource, sourceTemperature: Double) {
        val coefficient = when (sourceName) {
            TemperatureSource.OUTSIDE -> 0.005
            TemperatureSource.HOUSE -> 0.01
            TemperatureSource.ROOM -> 0.5
        }

        temperature += (sourceTemperature - temperature) * coefficient
    }


    fun genRoomByType(roomTypesJSON: Array<jRoomType>): ErrorType {
        for (roomTypeJSON in roomTypesJSON) {
            if (roomTypeString == roomTypeJSON.room_type) {
                roomType.genRoomType(roomTypeJSON)
            }
        }

        return ErrorType.OK
    }

    //------------------------------------------------------------------------------------------------------DEVICES ZONE

    private var devices = ArrayList<DeviceType>()

    fun getDevices() = devices

    fun genDevices(devicesJSON: Array<jDevice>) {
        devices.clear()
        for (deviceJSON in devicesJSON) {
            when (deviceJSON.device_type) {
                "THERMOMETER" -> devices.add(DeviceType.THERMOMETER)
                "CONDITIONER" -> devices.add(DeviceType.CONDITIONER)
                "COUNTER" -> devices.add(DeviceType.COUNTER)
            }
        }
    }

    //-----------------------------------------------------------------------------------------------INITIALIZATION ZONE

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