package renderer.sceneComputations

import kotlin.math.cos
import kotlin.math.sin

object MapRotation {
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
