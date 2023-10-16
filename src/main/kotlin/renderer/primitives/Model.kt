package renderer.primitives

import renderer.SceneGlobals
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.max

class Model(
    val modelName: String, pathToModel: String?
) {
    val vModel = ArrayList<FloatArray>()
    val fModel = ArrayList<IntArray>()

    var lastF = 0
    var normalizedSize = 0f
        private set


    //---------------------Остальное-----------------------
    private fun modelNormalize() {
        val nextCoord = floatArrayOf(0f, 0f, 0f)
        for (coords in vModel) {
            nextCoord[0] = coords[0]
            nextCoord[1] = coords[1]
            nextCoord[2] = coords[2]
            for (j in 0..2) normalizedSize = max(normalizedSize.toDouble(), abs(nextCoord[j].toDouble()))
                .toFloat()
        }
        normalizedSize = 1 / normalizedSize
        //System.out.println(normalizedSize);
    }

    /**
     * Получаем информацию о геометрических вершинах модели.
     * Получаем информацию о текстурных вершинах модели.
     */
    init {
        val fileInput: List<String>
        try {
            fileInput = Files.readAllLines(Paths.get(pathToModel))
            println()
            println(pathToModel)

            for (line in fileInput) {
                val elements = line.split(" ")
                if (elements[0] == "v") {
                    vModel.add(floatArrayOf(elements[1].toFloat(), elements[2].toFloat(), elements[3].toFloat()))
                }
                if (elements[0] == "f") {
                    fModel.add(intArrayOf(elements[1].toInt(), elements[2].toInt(), elements[3].toInt()))
                    println("---------------")
                    println(line)
                    println(elements)
                    println("" + elements[1].toInt() + " " + elements[2].toInt() + " " + elements[3].toInt())
                }
            }

            /*
            for (model in fModel)
                println("" + model[0] + " " + model[1] + " " + model[2])

             */

            modelNormalize()

        } catch (e: IOException) {
            e.printStackTrace()
            vModel.add(floatArrayOf(0.0f, 0.0f, 0.0f))
            fModel.add(intArrayOf(0, 0, 0))
            normalizedSize = 1f
            lastF = 0
        }
    }
}
