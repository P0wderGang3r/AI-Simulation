package environment.backrooms.classes

import environment.backrooms.json.jDevice
import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomPresets
import environment.globals.DeviceType
import environment.globals.TemperatureSource
import environment.iot.Conditioner
import environment.iot.Counter
import environment.iot.Thermometer
import environment.network.classes.Network
import environment.network.enums.ErrorType
import kotlin.math.abs

class Room {
    var header: String = ""

    var name: String = ""

    var coord_X: Int = 0

    var coord_Y: Int = 0

    var localNN: Network = Network()

    var localNNResolve = ArrayList<Double>()

    var roomTypeString: String = ""

    var roomPreset = RoomPreset()

    var connections = ArrayList<RoomConnection>()

    var connectionsString = ArrayList<String>()

    val lastControlActions = ArrayList<Int>()

    lateinit var tileModel: TileModel

    fun doNNResolve(day: Double) {
        if (day < 15.0)
            return

        if (abs(localNNResolve[0] - 1.0) < 0.00000001) {
            for (conditioner in conditioners)
                conditioner.isEnabled = true
        } else {
            for (conditioner in conditioners)
                conditioner.isEnabled = false
        }
    }

    fun nextStepNN(day: Double, time: Double) {
        val networkInput = ArrayList<Double>()

        //Парсим дни
        var inputDay = (day % 14).toInt()
        for (index in 0 until 4) {
            networkInput.add((inputDay % 2).toDouble())
            inputDay /= 2
        }

        //Парсим время
        var inputTime = time.toInt()
        for (index in 0 until 7) {
            networkInput.add((inputTime % 2).toDouble())
            inputTime /= 2
        }

        //Парсим температурные показатели
        if (thermometers.size > 0) {
            var inputTemperature = (thermometers[0].doTheWork(-1) - 14).toInt()
            for (index in 0 until 4) {
                networkInput.add((inputTemperature % 2).toDouble())
                inputTemperature /= 2
            }
        }

        //Парсим показатели количества людей
        if (counters.size > 0) {
            var inputCounter = counters[0].doTheWork(-1).toInt()
            for (index in 0 until 4) {
                networkInput.add((inputCounter % 2).toDouble())
                inputCounter /= 2
            }
        }

        localNNResolve = localNN.deepResolving(networkInput)
    }

    fun teachNN() {
        if (lastControlActions.size == 0)
            return

        if (lastControlActions[0] == -1)
            localNN.deepLearning(localNNResolve, 0)

        if (lastControlActions[0] != -1) {
            val localResolve = ArrayList<Double>()
            for (action in lastControlActions) {
                localResolve.add(action.toDouble())
            }
            localNN.deepLearning(localResolve, 0)
        }

        localNN.deepLearningUpdateWeights(0.1)
    }

    //------------------------------------------------------------------------------------------------------HANDLER ZONE

    fun enterHandler() {
        for (counter in counters) {
            counter.doTheWork(1)
        }
    }

    fun leaveHandler() {
        for (counter in counters) {
            counter.doTheWork(-1)
        }

        if (Math.random() > 0.1) {
            for (conditioner in conditioners) {
                conditioner.isEnabled = false
            }
        }
    }

    //-----------------------------------------------------------------------------------------------ЗОНА ГЕНЕРАЦИИ СТЕН

    fun getFloorModel() = tileModel.floorModel

    fun getWallModel() = tileModel.wallModel

    fun getLowWallModel() = tileModel.lowWallModel

    fun getWallColor() = tileModel.getWallColor()

    fun getFloorColor() = tileModel.getFloorColor(temperature)

    //-----------------------------------------------------------------------------------ЗОНА ГЕНЕРАЦИИ МЕСТ ПЕРЕМЕЩЕНИЯ

    fun genLocalNodes(): ArrayList<MoveNode> {
        val localMoveNodes = ArrayList<ArrayList<MoveNode>>()

        for (xIndex in 0 until roomPreset.size_X) {
            localMoveNodes.add(ArrayList())
            for (yIndex in 0 until roomPreset.size_Y) {
                //Новосозданная нода
                val localMoveNode = MoveNode(xIndex + coord_X, yIndex + coord_Y, this)

                localMoveNodes[xIndex].add(localMoveNode)

                //Составляю карту окрестности нода перемещения
                for (xOcclusionIndex in -1 .. 1) {
                    //Проверка, что соседняя нода по координате X находится в пределах комнаты
                    if (xIndex + xOcclusionIndex !in 0 until roomPreset.size_X) {
                        continue
                    }

                    for (yOcclusionIndex in -1 .. 1) {
                        //Проверка, что текущее смещение указывает не на текущую ноду
                        if (xOcclusionIndex == 0 && yOcclusionIndex == 0)
                            continue

                        //Проверка, что соседняя нода по координате Y находится в пределах комнаты
                        if (yIndex + yOcclusionIndex !in 0 until roomPreset.size_Y) {
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
            TemperatureSource.OUTSIDE -> 1.0
            TemperatureSource.HOUSE -> 0.01
            TemperatureSource.ROOM -> 0.66
            TemperatureSource.HUMAN -> 0.05
            TemperatureSource.RADIATOR -> 0.05
        }

        temperature += (sourceTemperature - temperature) * coefficient
    }

    //------------------------------------------------------------------------------------------------------DEVICES ZONE

    val conditioners = ArrayList<Conditioner>()

    val thermometers = ArrayList<Thermometer>()

    val counters = ArrayList<Counter>()

    fun genDevices(devicesJSON: Array<jDevice>) {
        conditioners.clear()
        thermometers.clear()
        counters.clear()
        for (deviceJSON in devicesJSON) {
            when (deviceJSON.device_type) {
                "thermometer" -> thermometers.add(Thermometer(this, DeviceType.THERMOMETER))
                "conditioner" -> conditioners.add(Conditioner(this, DeviceType.CONDITIONER))
                "counter" -> counters.add(Counter(this, DeviceType.COUNTER))
            }
        }
    }

    fun doUserControlAction() {
        if (lastControlActions.size > 0) {
            if (lastControlActions[0] == 0) {
                for (conditioner in conditioners)
                    conditioner.isEnabled = false
            }

            if (lastControlActions[0] == 1) {
                for (conditioner in conditioners)
                    conditioner.isEnabled = true
            }
        }
    }

    //-----------------------------------------------------------------------------------------------INITIALIZATION ZONE

    fun genRoom(housePath: String, roomJSON: jRoom, roomPresetsJSON: jRoomPresets, roomFloorNum: Int): ErrorType {
        this.header = roomJSON.header
        this.name = roomJSON.name
        this.coord_X = roomJSON.coord_X
        this.coord_Y = roomJSON.coord_Y
        this.roomTypeString = roomJSON.room_preset
        this.connectionsString.addAll(roomJSON.connections)

        localNN.genNeuralNetwork("${housePath}/${roomJSON.path_NN}")

        genDevices(roomJSON.devices)

        for (roomTypeJSON in roomPresetsJSON.rooms) {
            if (roomTypeString == roomTypeJSON.room_preset) {
                roomPreset.genRoomType(roomTypeJSON)
            }
        }

        val height = 2.0
        tileModel = TileModel(coord_X, coord_Y, roomFloorNum, height, roomPreset)
        tileModel.genFloor()
        tileModel.genWalls()
        tileModel.genLowWalls()

        return ErrorType.OK
    }
}