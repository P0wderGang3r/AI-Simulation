package runtime

class Runtime: Runnable {

    private val runtimeParams: RuntimeParams = RuntimeParams()

    fun getSimParams(): RuntimeParams = runtimeParams

    /**
     * Состояние потока симуляции
     */
    private var runtimeState: RuntimeState = RuntimeState.PAUSE

    /**
     * GETTER, возвращающий текущее состояние симуляции
     */
    fun getSimState(): RuntimeState = runtimeState

    /**
     * Запрос полной остановки работы потока симуляции
     */
    fun requestStop() {
        runtimeState = RuntimeState.STOP
    }

    /**
     * Запрос продолжения работы симуляции
     */
    fun requestRun() {
        runtimeState = when (runtimeState) {
            RuntimeState.STOP -> return
            RuntimeState.RUNNING -> runtimeState
            RuntimeState.RESET -> runtimeState

            else -> RuntimeState.READY
        }
    }

    /**
     * Запрос временной приостановки работы симуляции
     */
    fun requestPause() {
        runtimeState = when (runtimeState) {
            RuntimeState.STOP -> return
            RuntimeState.RESET -> runtimeState

            else -> RuntimeState.PAUSE
        }
    }

    /**
     * Запрос сброса накопленных данных во время работы симуляции
     */
    fun requestReset() {
        runtimeState = when (runtimeState) {
            RuntimeState.STOP -> return

            else -> RuntimeState.RESET
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

        runtimeState = when (runtimeState) {
            RuntimeState.RUNNING -> RuntimeState.READY

            else -> runtimeState
        }
    }

    /**
     * Основной цикл работы симуляции
     */
    override fun run() {
        while (runtimeState != RuntimeState.STOP) {
            when (runtimeState) {
                RuntimeState.STOP -> return
                RuntimeState.RESET -> resetSimulation()
                RuntimeState.READY -> {
                    runtimeState = RuntimeState.RUNNING
                    Thread(nextTick()).start()
                }

                else -> {}
            }
            Thread.sleep(runtimeParams.getSimTickDelay())
        }
    }

}