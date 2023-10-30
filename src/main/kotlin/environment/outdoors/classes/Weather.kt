package environment.outdoors.classes

import environment.globals.DateTime
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


    private var dayTemperatures = ArrayList<Day>()

    fun getTemperatures() = dayTemperatures

    fun getCurrentTemperature(): Double {
        val currentDay = outdoor.envGlobals.getExactDate(DateTime.DAYS).toInt()
        val currentHour = outdoor.envGlobals.getExactDate(DateTime.HOURS).toInt()
        val currentMinute = outdoor.envGlobals.getExactDate(DateTime.MINUTES).toInt()
        var currentTemperature = dayTemperatures[0].temperatures[0]

        var isFoundByHour = false

        //Проходимся по всем дням
        for (dayTemperature in dayTemperatures) {
            //Если мы нашли требуемый день, то выбираем первый попавшийся и...
            if (currentDay >= dayTemperature.day) {
                //... проходимся по всем температурным показаниям
                for (temperature in dayTemperature.temperatures) {
                    //Если мы ещё не нашли искомую температуру, то просто перебираем все температуры
                    if (!isFoundByHour)
                        currentTemperature = temperature

                    //Если мы врезались в подходящий час, то...
                    if (currentHour >= currentTemperature.hour) {
                        //... приостанавливаем перебор температуры
                        isFoundByHour = true

                        //Ищем наиболее подходящее время по минутам в подходящий час и...
                        if (currentHour == currentTemperature.hour &&
                            currentMinute >= currentTemperature.minute) {
                            currentTemperature = temperature
                            //... возвращаем наиболее подходящую температуру
                            return currentTemperature.temperature
                        }
                    }
                }
                break
            }
        }

        return currentTemperature.temperature
    }

    private fun genTemperatures(days: Array<jDay>) {
        for (dayJSON in days) {
            val day = Day(dayJSON.day, ArrayList())
            dayTemperatures.add(day)

            for (temperatureJSON in dayJSON.temperatures) {
                day.temperatures.add(
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