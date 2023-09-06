package runtime

class RuntimeParams {
    private var simTickDelay: Long = 10

    fun getSimTickDelay(): Long = simTickDelay

    fun setSimTickDelay(simTickDelay: Long) {
        this.simTickDelay = simTickDelay
    }

    fun setSimSpeed(simSpeed: Long) {
        this.simTickDelay = 1000 / simSpeed
    }

    //In milliseconds
    private var simTickTime: Double = 0.1

    fun getSimTickTime(): Double = simTickTime

    fun setSimTickTime(simTickTime : Double) {
        this.simTickTime = simTickTime
    }
}