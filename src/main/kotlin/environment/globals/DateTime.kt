package environment.globals

enum class DateTime {
    MILLISECONDS {
        override fun getPrecise(time: Double): Double {
            return ( ((SECONDS.getRaw(time) - SECONDS.getPrecise(time)) * 1000).toInt()).toDouble()
        }

        override fun getRaw(time: Double): Double {
            return (SECONDS.getRaw(time) - SECONDS.getPrecise(time)) * 1000
        }
    },
    SECONDS {
        override fun getPrecise(time: Double): Double {
            return ( ((MINUTES.getRaw(time) - MINUTES.getPrecise(time)) * 60).toInt()).toDouble()
        }

        override fun getRaw(time: Double): Double {
            return (MINUTES.getRaw(time) - MINUTES.getPrecise(time)) * 60
        }
    },
    MINUTES {
        override fun getPrecise(time: Double): Double {
            return ( ((HOURS.getRaw(time) - HOURS.getPrecise(time)) * 60).toInt()).toDouble()
        }

        override fun getRaw(time: Double): Double {
            return (HOURS.getRaw(time) - HOURS.getPrecise(time)) * 60
        }
    },
    HOURS {
        override fun getPrecise(time: Double): Double {
            return ( ((DAYS.getRaw(time) - DAYS.getPrecise(time)) * 24).toInt()).toDouble()
        }

        override fun getRaw(time: Double): Double {
            return (DAYS.getRaw(time) - DAYS.getPrecise(time)) * 24
        }
    },
    DAYS {
        override fun getPrecise(time: Double): Double {
            return (time.toInt() / 86400000).toDouble()
        }

        override fun getRaw(time: Double): Double {
            return time / 86400000
        }
    };

    abstract fun getPrecise(time: Double): Double

    abstract fun getRaw(time: Double): Double
}