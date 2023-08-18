package neural_network.enums

enum class ActivationFunction {
    NONE {
        override fun setDefaultValues(defaultValues: Array<Double>) {}

        override fun getDefaultValues(): Array<Double> {
            return arrayOf()
        }

        override fun calculate(signal: Double): Double {
            return signal
        }

    },
    THRESHOLD {
        //Threshold value of input signal
        private var defaultValues: Array<Double> = arrayOf(0.5)

        override fun setDefaultValues(defaultValues: Array<Double>) {
            this.defaultValues = defaultValues
        }

        override fun getDefaultValues(): Array<Double> {
            return defaultValues
        }

        override fun calculate(signal: Double): Double {
            if (signal > defaultValues[0])
                return 1.0
            return 0.0
        }

    }

    ;

    abstract fun setDefaultValues(defaultValues: Array<Double>)

    abstract fun getDefaultValues(): Array<Double>

    abstract fun calculate(signal: Double): Double
}