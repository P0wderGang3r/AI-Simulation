package environment.outdoors.classes

import environment.backrooms.classes.House
import environment.backrooms.json.jRoomPresets
import environment.globals.EnvGlobals
import environment.householders.Householder
import environment.householders.Householder1
import environment.householders.Householder2
import environment.householders.Householder3
import environment.network.enums.ErrorType
import environment.outdoors.json.jOutdoor
import jsonParser
import kotlinx.serialization.decodeFromString
import java.io.File

class Outdoor(
    val envGlobals: EnvGlobals
) {
    private var header = ""

    fun getHeader() = header

    //------------------------------------------------------------------------------------------------------------PEOPLE

    private val householders = ArrayList<Householder>()

    fun getHouseholders() = householders

    private fun initHouseholders() {
        householders.add(Householder1(house.getFloors()[0].getRooms()[0], householders))
        householders.add(Householder2(house.getFloors()[0].getRooms()[1], householders))
        householders.add(Householder3(house.getFloors()[0].getRooms()[2], householders))
    }

    //-------------------------------------------------------------------------------------------------ROOM PRESETS ZONE

    private fun parseRoomPresets(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jRoomPresetsDesc: jRoomPresets
        try {
            jRoomPresetsDesc = jsonParser.decodeFromString(jsonText)
        } catch (_: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jRoomPresetsDesc
        return result
    }

    //-------------------------------------------------------------------------------------------------------------House

    private val house = House(this)

    fun getHouse() = house

    //------------------------------------------------------------------------------------------------------WEATHER ZONE

    private var weather = Weather(this)

    fun getWeather() = weather


    //--------------------------------------------------------------------------------------------------Outdoor map path

    private var outdoorMap = String

    fun getOutdoorMap() = outdoorMap


    //------------------------------------------------------------------------------------------------Outdoor generation

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

    fun genOutdoor(environmentPath: String): ErrorType {
        val parseResult = parseOutdoor("$environmentPath/outdoor.json")
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jOutdoorJSON = parseResult.data as jOutdoor


        val parseRoomPresetsResult = parseRoomPresets("$environmentPath/${jOutdoorJSON.path_room_presets}")
        if (parseRoomPresetsResult != ErrorType.OK) {
            return parseRoomPresetsResult
        }
        val jRoomPresetsJSON = parseRoomPresetsResult.data as jRoomPresets

        header = jOutdoorJSON.header

        house.funHouseGen("$environmentPath/${jOutdoorJSON.path_house}", jRoomPresetsJSON)

        initHouseholders()

        weather.genWeather("$environmentPath/${jOutdoorJSON.path_weather}")

        /*
        for (temperature in weather.getTemperatures()) {
            for (localTemp in temperature.temperatures)
            println("${temperature.day} ${localTemp.hour} ${localTemp.minute} ${localTemp.temperature}")
        }
         */


        return ErrorType.OK
    }
}