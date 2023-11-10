package environment.householders

import environment.backrooms.classes.Room

class Householder2 (
    override var currentRoom: Room,
    override val householders: ArrayList<Householder>
    ): Householder {
    override val priority = 2

    override var mood = 50

    override var coord_X: Int = 0

    override var coord_Y: Int = 0

    override var accumulatedMood: Int = 0

    override var numberOfTries: Int = 0

    //Матрица переходов
    override val dayTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.2, 0.3, 0.5, 0.0, 0.0),
            doubleArrayOf(0.2, 0.0, 0.6, 0.2, 0.0),
            doubleArrayOf(0.05, 0.05, 0.9, 0.0, 0.0),
            doubleArrayOf(0.0, 0.7, 0.0, 0.0, 0.3),
            doubleArrayOf(0.0, 0.0, 0.0, 0.7, 0.3)
        )

    override val nightTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.8, 0.2, 0.0, 0.0, 0.0),
            doubleArrayOf(0.4, 0.0, 0.2, 0.4, 0.0),
            doubleArrayOf(0.6, 0.4, 0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.15, 0.0, 0.0, 0.85),
            doubleArrayOf(0.0, 0.0, 0.0, 0.15, 0.85)
        )

    //Минимальное и максимальное приемлемое значение температуры
    override val dayPreferredCondition: DoubleArray =
        doubleArrayOf(20.0, 22.0)
    override val nightPreferredCondition: DoubleArray =
        doubleArrayOf(17.5, 21.0)
}