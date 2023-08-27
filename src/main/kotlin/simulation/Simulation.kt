package simulation

class Simulation: Runnable {

    private val simParams: SimParams = SimParams()

    fun getSimParams(): SimParams = simParams

    /**
     * Состояние потока симуляции
     */
    private var simState: SimState = SimState.PAUSE

    /**
     * GETTER, возвращающий текущее состояние симуляции
     */
    fun getSimState(): SimState = simState

    /**
     * Запрос полной остановки работы потока симуляции
     */
    fun requestStop() {
        simState = SimState.STOP
    }

    /**
     * Запрос продолжения работы симуляции
     */
    fun requestRun() {
        simState = when (simState) {
            SimState.STOP -> return
            SimState.RUNNING -> simState
            SimState.RESET -> simState

            else -> SimState.READY
        }
    }

    /**
     * Запрос временной приостановки работы симуляции
     */
    fun requestPause() {
        simState = when (simState) {
            SimState.STOP -> return
            SimState.RESET -> simState

            else -> SimState.PAUSE
        }
    }

    /**
     * Запрос сброса накопленных данных во время работы симуляции
     */
    fun requestReset() {
        simState = when (simState) {
            SimState.STOP -> return

            else -> SimState.RESET
        }
    }

    /**
     * Сброс состояния симуляции
     */
    private fun resetSimulation() {
        //Здесь перечисление всех тех вещей, что должны быть очищены, сброшены и т.д.
    }

    /**
     * Выполнение следующего такта работы симуляции
     */
    private fun nextTick() = Runnable {
        println("WORK TICK")

        simState = when (simState) {
            SimState.RUNNING -> SimState.READY

            else -> simState
        }
    }

    /**
     * Основной цикл работы симуляции
     */
    override fun run() {
        while (simState != SimState.STOP) {
            when (simState) {
                SimState.STOP -> return
                SimState.RESET -> resetSimulation()
                SimState.READY -> {
                    simState = SimState.RUNNING
                    Thread(nextTick()).start()
                }

                else -> {}
            }
            Thread.sleep(simParams.getSimTickDelay())
        }
    }

}