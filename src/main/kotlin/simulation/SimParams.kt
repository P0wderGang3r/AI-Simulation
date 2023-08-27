package simulation

class SimParams {
    private var simTickDelay: Long = 10

    public fun getSimTickDelay(): Long = simTickDelay

    public fun setSimTickDelay(simTickDelay: Long) {
        this.simTickDelay = simTickDelay
    }

    public fun setSimSpeed(simSpeed: Long) {
        this.simTickDelay = 1000 / simSpeed
    }

    //In milliseconds
    private var simTickTime: Double = 0.1

    public fun getSimTickTime(): Double = simTickTime

    public fun setSimTickTime(simTickTime : Double) {
        this.simTickTime = simTickTime
    }
}