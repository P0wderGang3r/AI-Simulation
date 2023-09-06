package environment.outdoors

import environment.backrooms.classes.House

class Outdoor {
    private val houses = ArrayList<House>()

    fun getHouses() = houses

    //Temperature

    private var temperature = ArrayList<ArrayList<Temperature>>()

    fun getTemperature() = temperature

    fun addTemperature(temperature: Temperature) {
    }

    fun parseTemperature() {

    }

    //House generation------------------

    private fun funHouseParser() {

    }

    fun genHouse() {

    }
}