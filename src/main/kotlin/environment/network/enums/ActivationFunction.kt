package environment.network.enums

enum class ActivationFunction {
    NONE {
        /**
         * @param defaultValues Array[0]
         */
        override fun calculate(defaultValues: Array<Double>, signal: Double): Double {
            return signal
        }

        override fun derivative(inputValues: Array<Double>): Double {
            return 0.0
        }

    },
    THRESHOLD {
        //Threshold value of input signal
        private var defaultValues: Array<Double> = arrayOf(0.5)

        /**
         * @param defaultValues Array[1]
         */
        override fun calculate(defaultValues: Array<Double>, signal: Double): Double {
            if (signal > defaultValues[0])
                return 1.0
            return 0.0
        }

        override fun derivative(inputValues: Array<Double>): Double {
            return if (inputValues[0] < defaultValues[0]) 0.0 else 1.0
        }

    }

    ;

    abstract fun calculate(defaultValues: Array<Double>, signal: Double): Double

    abstract fun derivative(inputValues: Array<Double>): Double
}