package runtime

class RuntimeParams {
    //-----------------------------------------------------------------------------КОЛИЧЕСТВО ТАКТОВ СИМУЛЯЦИИ В СЕКУНДУ
    private var simTickDelay: Long = 500

    fun getSimTickDelay(): Long = simTickDelay

    fun setSimTickDelay(simTickDelay: Long) {
        this.simTickDelay = simTickDelay
    }

    fun setSimSpeed(simSpeed: Long) {
        this.simTickDelay = 1000 / simSpeed
    }

    //--------------------------------------------------------------------------------------------------КАДРОВАЯ ЧАСТОТА

    @Volatile
    private var framesPerSecond = 60

    fun getFramesPerSecond(): Int {
        return framesPerSecond
    }

    fun setFramesPerSecond(framesPerSecond: Int) {
        this.framesPerSecond = framesPerSecond
    }
}