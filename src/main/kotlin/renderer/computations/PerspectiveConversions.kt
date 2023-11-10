package renderer.computations

import kotlin.math.abs
import kotlin.math.tan

class PerspectiveConversions (
    width: Int,
    height: Int,
    private val near: Double,
    private val far: Double,
    private val fov: Double
) {
    private val aspect: Double

    /* *
    private final double[][] matrix = new double[4][4];

    private void initMatrix() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                matrix[j][i] = 0;

        double convXY = 1 / (Math.tan(fov * Math.PI / (2 * 180)) * aspect);

        matrix[0][0] = convXY;
        matrix[1][1] = convXY;
        matrix[2][2] = -(far) / (far - near);
        matrix[3][2] = -1;
        matrix[2][3] = -(2 * far * near) / (far - near);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println("");
        }
    }
    */
    fun getPerspectiveConversions(coords: FloatArray): FloatArray {
        val newCoords = floatArrayOf(coords[0], coords[1], coords[2])
        val dist = (abs(near) + abs(far)) / 2
        val perspectiveFunc = (dist - coords[2]) / dist * 1 / tan(fov * Math.PI / (2 * 180))
        newCoords[0] *= perspectiveFunc.toFloat()
        newCoords[1] *= perspectiveFunc.toFloat() * aspect.toFloat()
        return newCoords
    }

    init {
        aspect = width.toDouble() / height.toDouble()

        //initMatrix();
    }
}
