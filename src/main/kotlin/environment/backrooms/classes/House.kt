package environment.backrooms.classes

import environment.backrooms.json.jHouse
import environment.backrooms.json.jRoomTypes
import environment.globals.TemperatureSource
import jsonParser
import kotlinx.serialization.decodeFromString
import environment.network.classes.Network
import environment.network.enums.ErrorType
import environment.outdoors.classes.Outdoor
import java.io.File

class House (
    val outdoor: Outdoor
) {
    private var header = ""

    private var globalNN = Network()

    private val floors = ArrayList<Floor>()

    private val moveNodes = ArrayList<MoveNode>()

    fun genNodes() {
        for (floor in floors) {
            for (room in floor.getRooms()) {
                room.genLocalNodes()
            }
        }
    }

    //--------------------------------------------------------------------------------------------------TEMPERATURE ZONE

    fun calculateTemperature() {
        class TempByRoom(val temperature: Double, val room: Room)

        for (floor in floors) {
            val temperaturesByRoom = ArrayList<TempByRoom>()

            //Собираем информацию о температуре на этаже. ЭТО ВАЖНО!!!11!1!
            for (room in floor.getRooms()) {
                temperaturesByRoom.add(
                    TempByRoom(0.0 + room.getTemperature(), room)
                )
            }
            for (room in floor.getRooms()) {
                //Меняем температуру в комнате в соответствии с температурой окружающей среды
                room.changeTemperature(TemperatureSource.OUTSIDE, outdoor.getWeather().getCurrentTemperature())

                //Меняем температуру в комнате в соответствии с другими комнатами на текущем этаже
                for (temperatureByRoom in temperaturesByRoom) {
                    if (temperatureByRoom.room != room)
                        room.changeTemperature(
                            TemperatureSource.HOUSE,
                            temperatureByRoom.temperature
                        )
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------------FLOOR GENERATION ZONE

    private fun parseRoomTypes(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jRoomTypesDesc: jRoomTypes
        try {
            jRoomTypesDesc = jsonParser.decodeFromString(jsonText)
        } catch (_: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jRoomTypesDesc
        return result
    }

    private fun genFloorRoomsByType(path: String): ErrorType {
        val parseResult = parseRoomTypes(path)
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jRoomTypesJSON = parseResult.data as jRoomTypes

        for (floor in floors) {
            floor.genRoomsByType(jRoomTypesJSON.rooms)
        }

        return ErrorType.OK
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

        globalNN.genNeuralNetwork(jHouseJSON.path_NN)

        var index = 1
        for (floorJSON in jHouseJSON.floors) {
            val floor = Floor(index)
            floor.genFloor(floorJSON)
            floors.add(floor)
            index++
        }

        return ErrorType.OK
    }
}