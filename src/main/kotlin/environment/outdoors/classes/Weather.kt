package environment.outdoors.classes

import environment.network.enums.ErrorType
import environment.outdoors.json.jDay
import environment.outdoors.json.jWeather
import jsonParser
import kotlinx.serialization.decodeFromString
import java.io.File

class Weather(
    val outdoor: Outdoor
) {
    private var header = ""

    fun getHeader() = header


    private var temperatures = ArrayList<ArrayList<Temperature>>()

    fun getTemperatures() = temperatures

    fun getCurrentTemperature(): Double {
        val currentTemperature = outdoor.envGlobals.getSimulationTime()

        return TODO()
    }

    private fun genTemperatures(days: Array<jDay>) {
        for (dayJSON in days) {
            val dayTemperatures = ArrayList<Temperature>()
            temperatures.add(dayTemperatures)
            for (temperatureJSON in dayJSON.temperatures) {
                dayTemperatures.add(
                    Temperature(
                        temperatureJSON.hour,
                        temperatureJSON.minute,
                        temperatureJSON.temperature
                    )
                )
            }
        }
    }

    private fun parseWeather(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jWeatherDesc: jWeather
        try {
            jWeatherDesc = jsonParser.decodeFromString(jsonText)
        } catch (_: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jWeatherDesc
        return result

    }

    fun genWeather(path: String): ErrorType {
        //Temperature parse------------------
        val parseResult = parseWeather(path)
        if (parseResult != ErrorType.OK) {
            return parseResult
        }
        val jWeatherJSON = parseResult.data as jWeather

        header = jWeatherJSON.header

        genTemperatures(jWeatherJSON.days)

        return ErrorType.OK
    }
}