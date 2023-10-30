package environment.globals

class EnvGlobals {
    //-------------------------------------------------------------------------------------------ТЕКУЩЕЕ ВРЕМЯ СИМУЛЯЦИИ
    private var simulationTime = 0.0

    fun getSimulationTime(): Double = simulationTime

    fun getExactDate(dateTime: DateTime): Double = dateTime.getPrecise(simulationTime)

    fun nextSimulationTime() {
        simulationTime += deltaTime
    }

    //---------------------------------------------------------------------------------------ИЗМЕНЕНИЕ ВРЕМЕНИ СИМУЛЯЦИИ

    //In milliseconds
    private var deltaTime: Double = 1.0

    fun getDeltaTime(): Double = deltaTime

    fun setDeltaTime(deltaTime : Double) {
        this.deltaTime = deltaTime
    }
}