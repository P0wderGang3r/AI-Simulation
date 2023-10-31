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

    var name: String = ""

    var coord_X: Int = 0

    var coord_Y: Int = 0

    var localNN: Network = Network()

    var roomTypeString: String = ""

    var roomType = RoomType()

    var connections = ArrayList<RoomConnection>()

    var connectionsString = ArrayList<String>()

    fun genLocalNodes(): ArrayList<MoveNode> {
        val localMoveNodes = ArrayList<ArrayList<MoveNode>>()

        for (xIndex in 0 until roomType.size_X) {
            localMoveNodes.add(ArrayList())
            for (yIndex in 0 until roomType.size_Y) {
                //Новосозданная нода
                val localMoveNode = MoveNode(xIndex + coord_X, yIndex + coord_Y, this)

                localMoveNodes[xIndex].add(localMoveNode)

                //Составляю карту окрестности нода перемещения
                for (xOcclusionIndex in -1 .. 1) {
                    //Проверка, что соседняя нода по координате X находится в пределах комнаты
                    if (xIndex + xOcclusionIndex !in 0 until roomType.size_X) {
                        continue
                    }

                    for (yOcclusionIndex in -1 .. 1) {
                        //Проверка, что текущее смещение указывает не на текущую ноду
                        if (xOcclusionIndex == 0 && yOcclusionIndex == 0)
                            continue

                        //Проверка, что соседняя нода по координате Y находится в пределах комнаты
                        if (yIndex + yOcclusionIndex !in 0 until roomType.size_Y) {
                            continue
                        }

                        //Пробуем создать новое соединение с ближайшей нодой
                        try {
                            localMoveNode.connectedNodes.add(
                                localMoveNodes[xIndex + xOcclusionIndex][yIndex + yOcclusionIndex]
                            )
                        } catch (_: Exception) { }
                    }
                }
            }
        }

        val resultNodes = ArrayList<MoveNode>()

        for (nodes in localMoveNodes) {
            resultNodes.addAll(nodes)
        }

        return resultNodes
    }

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
        this.name = roomJSON.name
        this.coord_X = roomJSON.coord_X
        this.coord_Y = roomJSON.coord_Y
        this.roomTypeString = roomJSON.room_type
        this.connectionsString.addAll(roomJSON.connections)

        localNN.genNeuralNetwork(roomJSON.path_NN)

        genDevices(roomJSON.devices)

        return ErrorType.OK
    }
}