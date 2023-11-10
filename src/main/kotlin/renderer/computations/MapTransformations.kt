package renderer.computations

import kotlin.math.cos
import kotlin.math.sin

object MapTransformations {
    fun move(coords: FloatArray, positionStatus: FloatArray): FloatArray {
        return floatArrayOf(
            coords[0] + positionStatus[0],
            coords[1] + positionStatus[1],
            coords[2] + positionStatus[2]
        )
    }

    fun move1D(coords: FloatArray, move_Z: Float): FloatArray {
        return floatArrayOf(
            coords[0],
            coords[1],
            coords[2] + move_Z
        )
    }

    fun resize3D(coordinates: FloatArray, size: FloatArray): FloatArray {
        return floatArrayOf(
            coordinates[0] * size[0],
            coordinates[1] * size[1],
            coordinates[2] * size[2]
        )
    }

    fun resize(coordinates: FloatArray, size: Float): FloatArray {
        return floatArrayOf(
            coordinates[0] * size,
            coordinates[1] * size,
            coordinates[2] * size
        )
    }

    private val rotationMatrix = Array(3) { FloatArray(3) }
    private fun rotMatrix(alpha: Float, beta: Float, gamma: Float) {
        rotationMatrix[0][0] = (cos(alpha.toDouble()) * cos(beta.toDouble())).toFloat()
        rotationMatrix[1][0] =
            (cos(alpha.toDouble()) * sin(beta.toDouble()) * sin(gamma.toDouble()) - sin(alpha.toDouble()) * cos(gamma.toDouble())).toFloat()
        rotationMatrix[2][0] =
            (cos(alpha.toDouble()) * sin(beta.toDouble()) * cos(gamma.toDouble()) + sin(alpha.toDouble()) * sin(gamma.toDouble())).toFloat()
        rotationMatrix[0][1] = (sin(alpha.toDouble()) * cos(beta.toDouble())).toFloat()
        rotationMatrix[1][1] =
            (sin(alpha.toDouble()) * sin(beta.toDouble()) * sin(gamma.toDouble()) + cos(alpha.toDouble()) * cos(gamma.toDouble())).toFloat()
        rotationMatrix[2][1] =
            (sin(alpha.toDouble()) * sin(beta.toDouble()) * cos(gamma.toDouble()) - cos(alpha.toDouble()) * sin(gamma.toDouble())).toFloat()
        rotationMatrix[0][2] = (-sin(beta.toDouble())).toFloat()
        rotationMatrix[1][2] = (cos(beta.toDouble()) * sin(gamma.toDouble())).toFloat()
        rotationMatrix[2][2] = (cos(beta.toDouble()) * cos(gamma.toDouble())).toFloat()
    }

    fun rotate(coordinates: FloatArray, rotationStatus: FloatArray): FloatArray {
        rotMatrix(rotationStatus[0], rotationStatus[1], rotationStatus[2])
        val xyz = floatArrayOf(0f, 0f, 0f)
        for (i in 0..2) for (j in 0..2) {
            xyz[i] += coordinates[j] * rotationMatrix[i][j]
        }
        return floatArrayOf(xyz[0], xyz[1], xyz[2])
    }
}