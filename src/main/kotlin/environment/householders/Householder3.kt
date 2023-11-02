package environment.householders

import environment.backrooms.classes.Room

class Householder3 (
override var currentRoom: Room,
override val householders: ArrayList<Householder>
): Householder {
    override val priority = 3

    override var mood = 50

    override var coord_X: Int = 0

    override var coord_Y: Int = 0

    //Матрица переходов
    override val dayTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.1, 0.6, 0.3, 0.0, 0.0),
            doubleArrayOf(0.1, 0.0, 0.3, 0.6, 0.0),
            doubleArrayOf(0.1, 0.5, 0.4, 0.0, 0.0),
            doubleArrayOf(0.0, 0.4, 0.0, 0.0, 0.6),
            doubleArrayOf(0.0, 0.0, 0.0, 0.2, 0.8)
        )

    override val nightTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 0.4, 0.6, 0.0),
            doubleArrayOf(0.0, 0.6, 0.4, 0.0, 0.0),
            doubleArrayOf(0.0, 0.3, 0.0, 0.0, 0.7),
            doubleArrayOf(0.0, 0.0, 0.0, 0.05, 0.95)
        )

    //Минимальное и максимальное приемлемое значение температуры
    override val dayPreferredCondition: DoubleArray =
        doubleArrayOf(19.0, 22.0)
    override val nightPreferredCondition: DoubleArray =
        doubleArrayOf(17.5, 19.0)
}