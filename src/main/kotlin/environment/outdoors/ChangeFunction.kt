package environment.outdoors

enum class ChangeFunction {
    NONE {
        override fun getCurrentTemperature(): Double {
            TODO("Not yet implemented")
        }

    };

    abstract fun getCurrentTemperature(): Double
}