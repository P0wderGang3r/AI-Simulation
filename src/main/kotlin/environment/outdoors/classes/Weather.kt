package environment.outdoors.classes

import environment.network.default_NN.enums.ErrorType
import environment.outdoors.json.jDay
import environment.outdoors.json.jWeather
import jsonParser
import kotlinx.serialization.decodeFromString
import java.io.File

class Weather {
    private var header = ""

    public fun getHeader() = header



    private var temperatures = ArrayList<ArrayList<Temperature>>()

    public fun getTemperatures() = temperatures

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