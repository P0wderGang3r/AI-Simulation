package environment.outdoors

import environment.backrooms.classes.House
import environment.outdoors.json.jOutdoor
import environment.outdoors.json.jTemperature
import jsonParser
import kotlinx.serialization.decodeFromString
import network.default_NN.enums.ErrorType
import java.io.File

class Outdoor {
    private var header = ""
    private val houses = ArrayList<House>()

    fun getHouses() = houses

    //Temperature

    private var weather = ArrayList<ArrayList<Temperature>>()

    fun getWeather() = weather

    fun addWeather(temperature: Temperature) {
    }

    fun parseWeather(path: String): ErrorType {
        //TODO

        return ErrorType.OK
    }

    fun genWeather(path: String): ErrorType {
        //Temperature parse------------------
        val parseResult = parseWeather(path)
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jTemperatureJSON = parseResult.data as jTemperature

        return ErrorType.OK
    }

    //Outdoor generation----------------

    private fun parseOutdoor(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jOutdoorDesc: jOutdoor
        try {
            jOutdoorDesc = jsonParser.decodeFromString(jsonText)
        } catch (_: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jOutdoorDesc
        return result
    }

    fun genOutdoor(path: String): ErrorType {

        val parseResult = parseOutdoor(path)
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jOutdoorJSON = parseResult.data as jOutdoor

        header = jOutdoorJSON.header

        for (housePath in jOutdoorJSON.houses) {
            val newHouse = House()
            houses.add(newHouse)
            newHouse.funHouseGen(housePath)
        }

        return ErrorType.OK
    }
}