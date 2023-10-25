package environment.backrooms.classes

import environment.backrooms.json.jHouse
import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomType
import environment.backrooms.json.jRoomTypes
import jsonParser
import kotlinx.serialization.decodeFromString
import environment.network.default_NN.classes.Network
import environment.network.default_NN.enums.ErrorType
import java.io.File
import java.util.Arrays

class House {
    private var header = ""

    private var globalNN = Network()

    private val floors = ArrayList<Floor>()

    //Floor co-generation---------------

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