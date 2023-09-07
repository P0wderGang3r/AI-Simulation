package environment.backrooms.classes

import environment.backrooms.json.jHouse
import jsonParser
import kotlinx.serialization.decodeFromString
import network.default_NN.classes.Network
import network.default_NN.enums.ErrorType
import java.io.File

class House {
    private var header = ""

    private lateinit var globalNN: Network

    private val floors = ArrayList<Floor>()

    fun getFloors() = floors

    //Devices---------------------------
    fun addDevice(floor: Int, room: String, device: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        var savedRoom: Room? = null
        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedRoom = iterRoom
                break
            }
        }

        if (savedRoom == null)
            return

        for (iterDevice in savedRoom.getDevices()) {
            if (iterDevice.name == device) {
                savedRoom.getDevices().remove(iterDevice)
                return
            }
        }
    }

    fun removeDevice(floor: Int, room: String, device: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        var savedRoom: Room? = null
        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedRoom = iterRoom
                break
            }
        }

        if (savedRoom == null)
            return

        for (iterDevice in savedRoom.getDevices()) {
            if (iterDevice.name == device) {
                savedRoom.getDevices().remove(iterDevice)
                return
            }
        }
    }

    //Rooms-----------------------------

    fun addRoom(floor: Int, room: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedFloor.getRooms().add(iterRoom)
                return
            }
        }
    }

    fun removeRoom(floor: Int, room: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedFloor.getRooms().add(iterRoom)
                return
            }
        }

    }

    //Floors----------------------------

    fun addFloor(floor: Int) {
        floors.add(Floor(floor))
    }

    fun removeFloor(floor: Int) {
        var savedFloor: Floor
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                floors.remove(iterFloor)
                return
            }
        }
    }

    //House generation------------------

    private fun parseHouse(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jHouseDesc: jHouse
        try {
            jHouseDesc = jsonParser.decodeFromString(jsonText)
        } catch (_: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jHouseDesc
        return result
    }

    fun funHouseGen(path: String): ErrorType {

        val parseResult = parseHouse(path)
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jHouseJSON = parseResult.data as jHouse

        header = jHouseJSON.header

        globalNN = Network()
        globalNN.genNeuralNetwork(jHouseJSON.path_NN)
        globalNN.printNetwork()

        for (floor in jHouseJSON.floors) {
            for (room in floor) {

            }
        }

        return ErrorType.OK
    }
}