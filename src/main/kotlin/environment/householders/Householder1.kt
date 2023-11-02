package environment.householders

import environment.backrooms.classes.Room

class Householder1 (
    override var currentRoom: Room,
    override val householders: ArrayList<Householder>
): Householder {
    override val priority = 1

    override var mood = 50

    override var coord_X: Int = 0

    override var coord_Y: Int = 0


    //Матрица переходов
    override val dayTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.9, 0.05, 0.05, 0.0, 0.0),
            doubleArrayOf(0.6, 0.0, 0.1, 0.3, 0.0),
            doubleArrayOf(0.6, 0.1, 0.3, 0.0, 0.0),
            doubleArrayOf(0.0, 0.7, 0.0, 0.0, 0.3),
            doubleArrayOf(0.0, 0.0, 0.0, 0.7, 0.3)
        )

    override val nightTimeMovementMatrix: Array<DoubleArray> =
        arrayOf(
            doubleArrayOf(0.3, 0.6, 0.1, 0.0, 0.0),
            doubleArrayOf(0.1, 0.0, 0.4, 0.5, 0.0),
            doubleArrayOf(0.7, 0.3, 0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.1, 0.0, 0.0, 0.9),
            doubleArrayOf(0.0, 0.0, 0.0, 0.05, 0.95)
        )

    //Минимальное и максимальное приемлемое значение температуры
    override val dayPreferredCondition: DoubleArray =
        doubleArrayOf(17.0, 20.5)
    override val nightPreferredCondition: DoubleArray =
        doubleArrayOf(17.5, 19.0)
}